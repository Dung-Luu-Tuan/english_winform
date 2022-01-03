package com.example.english.dao;

import com.example.english.models.phongthi;
import com.example.english.models.thisinh;
import com.example.english.models.thisinh_phongthi;
import com.example.english.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class thisinhphongthiDAO {
    private static SessionFactory factory;

    public static List<thisinh_phongthi> list() {
        List<thisinh_phongthi> thisinh_phongthi = null;
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            thisinh_phongthi = session.createQuery("from thisinh_phongthi").list();
            return thisinh_phongthi;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public static void insert(thisinh_phongthi thisinh_phongthi) {
        if (thisinh_phongthi != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(thisinh_phongthi);
            tx.commit();
            session.close();
        }
    }

    public static void deleteById(int id){
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        thisinh_phongthi thisinh_phongthi = session.get(thisinh_phongthi.class, id);
        session.delete(thisinh_phongthi);
        tx.commit();
        session.close();
    }

    public static void update(thisinh_phongthi thisinh_phongthi) {
        if (thisinh_phongthi != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(thisinh_phongthi);
            tx.commit();
            session.close();
        }
    }
}
