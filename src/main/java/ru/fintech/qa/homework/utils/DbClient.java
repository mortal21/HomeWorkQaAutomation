package ru.fintech.qa.homework.utils;

import org.hibernate.Session;
import ru.fintech.qa.homework.utils.models.Animal;
import ru.fintech.qa.homework.utils.models.Places;
import ru.fintech.qa.homework.utils.models.Zoo;

import java.util.List;

public class DbClient {
    public final List<Animal> count(final String name) {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        List<Animal> result = session.createNativeQuery("SELECT * FROM Animal", Animal.class).list();
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

    public final List<Places> countPlaces() {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        List<Places> result = session.createNativeQuery("SELECT * FROM Places", Places.class).list();
        session.close();
        return result;
    }

    public final List<Zoo> getZoos() {
        Session session = HibernateSessionCreator.getSessionFactory().openSession();
        List<Zoo> result = session.createNativeQuery("SELECT * FROM Zoo", Zoo.class).getResultList();
        session.close();
        return result;
    }
}