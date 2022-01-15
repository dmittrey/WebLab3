package com.example.WebLab3.dao;

import com.example.WebLab3.exceptions.UnfilledCredentialsException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
public class PersistenceFactory {

    private EntityManagerFactory entityManagerFactory;
    private final String dbLoginEnvName = "DB_LOGIN";
    private final String dbPasswordEnvName = "DB_PASSWORD";

    @PostConstruct
    private void entityManagerFactoryInit() throws UnfilledCredentialsException {
        log.info("Getting DB credentials!");

        Map<String, String> settings = new HashMap<>();
        settings.put("javax.persistence.jdbc.user", System.getenv(dbLoginEnvName));
        settings.put("javax.persistence.jdbc.password", System.getenv(dbPasswordEnvName));

        if (settings.get("javax.persistence.jdbc.user") != null
                && settings.get("javax.persistence.jdbc.password") != null) {

            log.info("Credentials filled successful!");

            try {
                entityManagerFactory = createEntityManagerFactory(settings);
            } catch (Exception e) {
                log.warn("Unable to connect! Setting helios options...");

                settings.put("javax.persistence.jdbc.url", "jdbc:postgresql://pg:5432/studs");
                entityManagerFactory = createEntityManagerFactory(settings);
            }
        } else throw new UnfilledCredentialsException(dbLoginEnvName, dbPasswordEnvName);
    }

    private EntityManagerFactory createEntityManagerFactory(Map<String, String> persistenceSettings) {
        return Persistence.createEntityManagerFactory("Users", persistenceSettings);
    }
}