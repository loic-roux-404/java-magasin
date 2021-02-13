package com.app.Services;

import com.app.Framework.Service;
import com.app.Utils.SessionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityManagerProxy extends SessionUtils implements Service {

    boolean loaded = false;

    Class entityClass;
    SessionFactory sessionFactory;
    EntityManager manager;

    public EntityManagerProxy(Class entityClass) {
        this.entityClass = entityClass;
        sessionFactory = get();
        manager = sessionFactory.createEntityManager();
        load();
    }

    public List<IEntity> getAll() {
        return manager.createQuery("from " + entityClass.getSimpleName()).getResultList();
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
            rollback((Transaction) transaction);
            throw (e);
        }
    }

    public int hqlTruncate(String myTable){
        String hql = String.format("delete from %s", myTable);
        return manager.createQuery(hql).executeUpdate();
    }

    public Class getEntityClass() {
        return entityClass;
    }

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
