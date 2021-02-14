package com.app.Services;

import com.app.Exceptions.EntityManagerProxyException;
import com.app.Framework.Service;
import com.app.Utils.SessionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EntityManagerProxy extends SessionUtils implements Service {

    boolean loaded = false;

    Class entityClass;
    Session session;
    Transaction tx;

    public EntityManagerProxy(Class entityClass) {
        this.entityClass = entityClass;
        session = getSession();
        load();
    }

    public List<IEntity> getAll() throws EntityManagerProxyException {
        List<IEntity> entities;
        session = getSession();

        try {
            entities = session.createQuery("from " + entityClass.getSimpleName()).list();
        } catch (Exception e) {
            throw new EntityManagerProxyException(e);
        } finally {
            close(session);
        }

        return entities;
    }

    /**
     * ACID add
     * @param en
     */
    public void add(IEntity en) throws EntityManagerProxyException {
        try {
            startOperation();
            session.saveOrUpdate(en);
            tx.commit();
        } catch (Exception e) {
            rollback(tx);
            throw new EntityManagerProxyException(e);
        } finally {
            close(session);
        }
    }

    public final void hqlTruncate(String myTable) throws EntityManagerProxyException {
        startOperation();
        String hql = String.format("delete from %s", myTable);

        try {
            session.createQuery(hql).executeUpdate();
        } catch (Exception exception) {
            throw new EntityManagerProxyException(exception);
        } finally {
            close(session);
        }

    }

    public final void startOperation() {
        session = getSession();
        tx = session.beginTransaction();
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
