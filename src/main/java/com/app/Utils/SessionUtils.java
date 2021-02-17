package com.app.Utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * class SessionUtils (used in a DAO system)
 * prepare a persistence session to make transactions in db
 */
public class SessionUtils {
    private static SessionFactory sessionFactory;

    static {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Builds a SessionFactory, if it hasn't been already.
     * @return sessionFactory getter
     */
    public static SessionFactory get() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Important to close persistance session after transaction
     * @param session Session to close
     */
    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    /**
     * Util function to secure a transaction rollback
     * @param tx Transaction
     */
    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {
            System.err.println("Couldn't rollback Transaction " + ignored.getMessage());
        }
    }
}