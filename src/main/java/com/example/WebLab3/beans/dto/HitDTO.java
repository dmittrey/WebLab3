package com.example.WebLab3.beans.dto;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.entity.User;
import com.example.WebLab3.interfaces.HitDTOInterface;
import com.example.WebLab3.utility.PersistenceFactory;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
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
    public void saveHit(Hit aHit) {
        User user = aHit.getUser();
        if (!isUserInTable(user)) initializeUser(user);
        saveObject(aHit);
    }

    @Override
    public Optional<List<Hit>> getSessionHitList(User anUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Hit> hitList = null;
        try {
            hitList = entityManager.find(User.class, anUser.getSessionId()).getHitList();
        } catch (Exception ex) {
            logger.warn("Exception at getSessionEntityList!");
            ex.printStackTrace();
        }

        entityManager.close(); // Это легковес, я могу так делать без потери производительности
        return Optional.ofNullable(hitList);
    }

    @Override
    public void deleteSessionEntityList(User anUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User deletingUser = entityManager.find(User.class, anUser.getSessionId());
            entityManager.remove(deletingUser);
            entityTransaction.commit();
        } catch (Exception ex) {
            try {
                logger.warn("Exception at deleteSessionEntityList!");
                entityTransaction.rollback();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        entityManager.close();
    }

    private void initializeUser(User anUser) {
        saveObject(anUser);
    }

    private boolean isUserInTable(User anUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.sessionId=:userSession");
            query.setParameter("userSession", anUser.getSessionId());
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            logger.info("No users in table with this sessionId!");
        } catch (Exception e) {
            logger.warn("Exception at is UserInTable!");
            e.printStackTrace();
        }
        entityManager.close(); // Это легковес, я могу так делать без потери производительности
        return false;
    }

    private void saveObject(Object anObject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(anObject);
            entityTransaction.commit();
        } catch (Exception e) {
            try {
                logger.warn("Exception at addEntity!");
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }
}