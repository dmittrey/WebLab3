package com.example.WebLab3.dto;

import com.example.WebLab3.entity.Hit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ManagedBean
@SessionScoped
public class HitServiceDTO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ManagedProperty(value = "#{persistenceFactory}")
    private PersistenceFactory persistenceFactory;

    public boolean save(Hit aHit) {
        EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
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
        EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
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

        EntityManager entityManager = persistenceFactory.getEntityManagerFactory().createEntityManager();
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
