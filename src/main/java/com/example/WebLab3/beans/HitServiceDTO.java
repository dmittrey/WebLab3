package com.example.WebLab3.beans;

import com.example.WebLab3.entity.Hit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

@ManagedBean(name = "hitServiceDTO")
@SessionScoped
public class HitServiceDTO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EntityManagerFactory entityManagerFactory;

    //todo Вынести в отдельный класс фабрику и понять как задать очередность загрузки бинов

    // todo Вынести в отдельный пакет классы связанные с БД
    @PostConstruct
    public void init() {
        Map<String, String> settings = new HashMap<>();

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

    public boolean save(Hit aHit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(aHit);
            entityTransaction.commit();

            entityManager.close();
            return true;
        } catch (Exception e) {
            try {
                logger.warn("Exception at addEntity!");
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
        return false;
    }

    public Optional<List<Hit>> getSessionEntityList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Hit> hitList = null;
        try {
            hitList = entityManager.createQuery("SELECT p FROM Hit p", Hit.class).getResultList();
        } catch (Exception ex) {
            logger.warn("Exception at getSessionEntityList!");
            ex.printStackTrace();
        }
        entityManager.close();
        return Optional.ofNullable(hitList);
    }

    public boolean deleteSessionEntityList() {
        List<Hit> hitList = getSessionEntityList().orElse(Collections.emptyList());

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            for (Hit hit : hitList) {
                entityManager.remove(entityManager.contains(hit) ? hit : entityManager.merge(hit));
            }
            entityTransaction.commit();
            return true;
        } catch (Exception ex) {
            try {
                logger.warn("Exception at deleteSessionEntityList.");
                entityTransaction.rollback();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        entityManager.close();
        return false;
    }
}
