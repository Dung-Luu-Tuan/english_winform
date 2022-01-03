package com.example.english.dao;

import com.example.english.models.khoathi;
import com.example.english.models.thisinh;
import com.example.english.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class thisinhDAO {
    private static SessionFactory factory;

    public static List<thisinh> listthisinh() {
        List<thisinh> thisinh = null;
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            thisinh = session.createQuery("from thisinh").list();
            return thisinh;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public static void insert(thisinh thisinh) {
        if (thisinh != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(thisinh);
            tx.commit();
            session.close();
        }
    }

    public static void delete(thisinh thisinh) {
        if (thisinh != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(thisinh);
            tx.commit();
            session.close();
        }
    }

    public static void deleteById(int id) {
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        thisinh thisinh = session.get(thisinh.class, id);
        if (thisinh != null) {
            session.delete(thisinh);
            tx.commit();
            session.close();
        }
    }

    public static void update(thisinh thisinh) {
        if (thisinh != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(thisinh);
            tx.commit();
            session.close();
        }
    }

    public static thisinh getThisinhById(int id) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            thisinh thisinh = session.get(thisinh.class, id);
            tx.commit();
            session.close();
            return thisinh;
        }
}
