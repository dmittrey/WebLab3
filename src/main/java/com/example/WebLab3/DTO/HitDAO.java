package com.example.WebLab3.DTO;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.utility.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HitDAO {

    public void save(Hit hit) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(hit);
        transaction.commit();
        session.close();
    }

    public List<Hit> sessionHitList(String sessionId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String queryString = "from Hit where sessionId= :value";
        return (List<Hit>) session.createQuery(queryString).list();
    }

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Hit").executeUpdate();
        transaction.commit();
        session.close();
    }
}
