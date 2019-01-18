package ru.sfedu.sevenTravel.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {


    private static EntityManagerFactory emf;

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            emf = Persistence.createEntityManagerFactory("taxi");
            return emf;
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getSessionFactory() {
        if (emf==null || !emf.isOpen()) return buildEntityManagerFactory();
        return emf;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


}
