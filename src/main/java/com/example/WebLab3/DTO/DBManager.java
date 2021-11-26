package com.example.WebLab3.DTO;

import com.example.WebLab3.entity.Hit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ApplicationScoped
public class DBManager {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence-Hibernate");
    private final EntityManager entityManager = emf.createEntityManager();

    @PostConstruct
    public void initial() {
        entityManager.getTransaction().begin();
    }

    public void persistHit(Hit hit) {
        entityManager.persist(hit);
    }

    public void sessionHitList() {
        System.out.println(111);
//        Session session = sessionFactory.getCurrentSession();
//        String queryString = "from Author where sessionId= :value";
//        Query queryObject = session.createQuery(queryString);
//        queryObject.setParameter("value", sessionId);
//        List<Hit> hitList = queryObject.list();
    }

    @PreDestroy
    public void destroy() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
