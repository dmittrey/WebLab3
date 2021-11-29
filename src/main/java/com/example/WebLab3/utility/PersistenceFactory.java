package com.example.WebLab3.utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PersistenceFactory {
    private static PersistenceFactory instance;

    public static PersistenceFactory getInstance() {
        if (instance == null) instance = new PersistenceFactory();
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        Map<String, String> settings = new HashMap<>();
        settings.put("javax.persistence.jdbc.user", System.getenv("login"));
        settings.put("javax.persistence.jdbc.password", System.getenv("password"));

        try {
            return createEntityManagerFactory(settings);
        } catch (Exception e) {
            System.err.println("Unable to connect! Setting helios options...");
            try {
                settings.put("javax.persistence.jdbc.url", "jdbc:postgresql://pg:5432/studs");
                return createEntityManagerFactory(settings);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return null;
    }

    private EntityManagerFactory createEntityManagerFactory(Map<String, String> persistenceSettings) {
        return Persistence.createEntityManagerFactory("Users", persistenceSettings);
    }
}