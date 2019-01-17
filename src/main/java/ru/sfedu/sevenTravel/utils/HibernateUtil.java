package ru.sfedu.sevenTravel.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {


    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("taxi");

    private static EntityManagerFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getSessionFactory() {
        return emf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


}
