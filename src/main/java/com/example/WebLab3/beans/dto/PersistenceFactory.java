package com.example.WebLab3.beans.dto;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Data
@ManagedBean
@NoneScoped
public class PersistenceFactory {

    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    private void entityManagerFactoryInit() {
        Map<String, String> settings = new HashMap<>();
        settings.put("javax.persistence.jdbc.user", System.getenv("login"));
        settings.put("javax.persistence.jdbc.password", System.getenv("password"));

        try {
            entityManagerFactory = createEntityManagerFactory(settings);
        } catch (Exception e) {
            System.err.println("Unable to connect! Setting helios options...");
            try {
                settings.put("javax.persistence.jdbc.url", "jdbc:postgresql://pg:5432/studs");
                entityManagerFactory = createEntityManagerFactory(settings);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    private EntityManagerFactory createEntityManagerFactory(Map<String, String> persistenceSettings) {
        return Persistence.createEntityManagerFactory("Users", persistenceSettings);
    }
}