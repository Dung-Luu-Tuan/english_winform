package com.example.english.dao;

import com.example.english.models.khoathi;
import com.example.english.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class khoathiDAO {
    private static SessionFactory factory;

    public static List<khoathi> listkhoathi() {
        List<khoathi> khoathi = null;
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        try {
            khoathi = session.createQuery("from khoathi").list();
            return khoathi;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public static khoathi getDetail(int id) {
        khoathi khoathi = null;
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.clear();
        try {
            session.beginTransaction();
            khoathi = session.find(khoathi.class, id);
            return khoathi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public static void insert(khoathi khoathi) {
        if (khoathi != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(khoathi);
            tx.commit();
            session.close();
        }
    }

    public static khoathi getByid(int id) {
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        khoathi khoathi = session.get(khoathi.class, id);
        tx.commit();
        session.close();
        return khoathi;
    }



    public static void delete(khoathi khoathi) {
        if (khoathi != null) {
            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(khoathi);
            tx.commit();
            session.close();
        }
    }

    public static void deleteById(int id) {
        factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        khoathi khoathi = session.get(khoathi.class, id);
        if (khoathi != null) {
            session.delete(khoathi);
            tx.commit();
            session.close();
        }
    }

    public static void update(khoathi khoathi) {
        if (khoathi != null) {
//            factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.update(khoathi);
            tx.commit();
            session.close();
        }
    }

    public static String priceWithoutDecimal(float price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price);
    }

    public static String DateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public static String DateFormat2(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public static final LocalDate LOCAL_DATE (Date date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse((CharSequence) date, formatter);
        return localDate;
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static Date DateFormat3(String date) throws ParseException {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date oldDate = (Date) formatter1.parse(date);
        String changeDate = formatter.format(oldDate);
        Date newDate = (Date) formatter1.parse(changeDate);
        return newDate;
    }
}
