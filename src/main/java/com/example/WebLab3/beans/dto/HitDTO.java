package com.example.WebLab3.beans.dto;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.interfaces.HitDTOInterface;
import com.example.WebLab3.utility.PersistenceFactory;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.*;

@Data
@ManagedBean(eager = true)
@SessionScoped
public class HitDTO implements HitDTOInterface {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    private void init() {
        entityManagerFactory = PersistenceFactory.getInstance().getEntityManagerFactory();
    }

    @Override
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

    @Override
    public Optional<List<Hit>> getSessionEntityList(String sessionId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Hit> hitList = null;
        try {
            Query query = entityManager.createQuery("SELECT u FROM Hit u WHERE u.sessionId=:sessionId");
            query.setParameter("sessionId", sessionId);
            hitList = query.getResultList();
        } catch (Exception ex) {
            logger.warn("Exception at getSessionEntityList!");
            ex.printStackTrace();
        }
        entityManager.close(); // Это легковес, я могу так делать без потери производительности
        return Optional.ofNullable(hitList);
    }

    @Override
    public boolean deleteSessionEntityList(String sessionId) {
        List<Hit> hitList = getSessionEntityList(sessionId).orElse(Collections.emptyList());

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
                logger.warn("Exception at deleteSessionEntityList!");
                entityTransaction.rollback();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        entityManager.close();
        return false;
    }
}