package com.example.WebLab3.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@SessionScoped
public class PersistenceFactory {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value = "#{authDetails}")
    private AuthDetails authDetails;

    @Getter
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        Map<String, String> settings = new HashMap<>();
        settings.put("javax.persistence.jdbc.user", authDetails.getLogin());
        settings.put("javax.persistence.jdbc.password", authDetails.getPassword());

        try {
            entityManagerFactory = initHitManagerFactory(settings);
        } catch (Exception ex) {
            logger.info("Unable to connect to DB! Setting helios settings!");
            try {
                settings.put("javax.persistence.jdbc.url", "jdbc:postgresql://pg:5432/studs");
                entityManagerFactory = initHitManagerFactory(settings);
            } catch (Exception e) {
                logger.warn("Unable to connect to DB again! Try to restart application!");
            }
        }
    }

    private EntityManagerFactory initHitManagerFactory(Map<String, String> settings) {
        return Persistence.createEntityManagerFactory("Hits", settings);
    }
}