package com.example.WebLab3.dao;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.entity.User;
import com.example.WebLab3.interfaces.OneToManyDAO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.*;

@Slf4j
@Data
@ManagedBean
@SessionScoped
public class UserToHitsDAO implements OneToManyDAO<Hit, User> {

    @ManagedProperty(value = "#{persistenceFactory.entityManagerFactory}")
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void saveUnit(Hit unitObj) {
        persistObject(unitObj);
    }

    @Override
    public void initOwner(User ownerObj) {
        persistObject(ownerObj);
    }

    @Override
    public void removeOwner(User ownerObj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User deletingOwner = entityManager.find(User.class, ownerObj.getSessionId());
            entityManager.remove(deletingOwner);
            entityTransaction.commit();
        } catch (Exception e) {
            try {
                log.warn("Exception while removing an {}!", ownerObj);
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }

    @Override
    public Optional<List<Hit>> getOwnerUnitsList(User ownerObj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Hit> hitList = null;
        try {
            hitList = entityManager.find(User.class, ownerObj.getSessionId()).getHitList();
        } catch (Exception ex) {
            log.warn("Exception while getting {} hitList!", ownerObj);
            ex.printStackTrace();
        }
        entityManager.close(); // Это легковес, я могу так делать без потери производительности
        return Optional.ofNullable(hitList);
    }

    @Override
    public void deleteOwnerUnits(User ownerObj) {
        removeOwner(ownerObj);
        mergeObject(ownerObj);
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
                log.warn("Exception while persisting {}", anObject);
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }

    private void mergeObject(Object anObject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(anObject);
            entityTransaction.commit();
        } catch (Exception e) {
            try {
                log.warn("Exception while merging {}", anObject);
                entityTransaction.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        entityManager.close();
    }
}