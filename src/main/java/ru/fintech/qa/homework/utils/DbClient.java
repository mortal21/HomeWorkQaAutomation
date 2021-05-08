package ru.fintech.qa.homework.utils;

import org.hibernate.Session;
import ru.fintech.qa.homework.utils.models.Zoo;

import java.util.List;

public class DbClient {
    public static <T> List count(final T object) {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        String s = "SELECT * FROM " + object;
        List result = session.createNativeQuery(s).list();
        session.close();
        return result;
    }

    public static <T> boolean insert(final T object) {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public static List<Zoo> getZoos() {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        List<Zoo> result = session.createNativeQuery("SELECT * FROM Zoo", Zoo.class).list();
        session.close();
        return result;
    }
}