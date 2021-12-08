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
    public void initUser(User anUser) {
        persistObject(anUser);
    }

    @Override
    public void saveHit(Hit aHit) {
        persistObject(aHit);
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
    public void deleteUserHits(User anUser) {
        removeUser(anUser);
        persistObject(anUser);
    }

    @Override
    public void removeUser(User anUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            User deletingUser = entityManager.find(User.class, anUser.getSessionId());
            entityManager.remove(deletingUser);

            entityTransaction.commit();
        } catch (Exception e) {
            try {
                logger.warn("Exception at removeEntity!");
                e.printStackTrace();
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }

    private void persistObject(Object anObject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(anObject);
            entityTransaction.commit();
        } catch (Exception e) {
            try {
                logger.warn("Exception at persistEntity!");
                e.printStackTrace();
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }
}