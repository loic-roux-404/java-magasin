package com.app.Services;

import com.app.Framework.Service;
import com.app.Utils.SessionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityManagerProxy extends SessionUtils implements Service {

    boolean loaded = false;

    Class enClass;
    SessionFactory sessionFactory;
    EntityManager manager;

    public EntityManagerProxy(Class classObj) {
        enClass = classObj;
        sessionFactory = get();
        manager = sessionFactory.createEntityManager();
    }

    public List<IEntity> getAll() {
        return manager.createQuery("from " + enClass.getSimpleName()).getResultList();
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
