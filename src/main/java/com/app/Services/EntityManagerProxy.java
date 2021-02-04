package com.app.Services;

import com.app.Framework.Service;
import com.app.Utils.SessionUtils;
import org.hibernate.SessionFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityManagerProxy extends SessionUtils implements Service {

    boolean loaded = false;

    Class clazz;
    SessionFactory sessionFactory;
    EntityManager manager;

    public EntityManagerProxy(Class clazz) {
        this.clazz = clazz;
        sessionFactory = get();
        manager = sessionFactory.createEntityManager();
    }

    public List<IEntity> getAll() {
        return manager.createQuery("from " + clazz.getSimpleName()).getResultList();
    }

    /**
     * ACID add
     * @param en
     */
    public void add(IEntity en) {
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(en);
            transaction.setRollbackOnly();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw (e);
        }
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return false;
    }
}
