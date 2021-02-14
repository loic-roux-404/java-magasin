package com.app.Services;

import com.app.Exceptions.EntityManagerProxyException;
import com.app.Framework.Service;
import com.app.Utils.SessionUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.transaction.Transactional;
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

    /**
     * Get all from entity
     *
     * @return
     * @throws EntityManagerProxyException
     */
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
     * ACID custom persist
     *
     * @param en
     */
    @Transactional
    public void persist(IEntity en) throws EntityManagerProxyException {
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

    /**
     * Empty a table
     *
     * @throws EntityManagerProxyException
     */
    public final void hqlTruncate() throws EntityManagerProxyException {
        startOperation();
        String hql = String.format("delete from %s", entityClass.getSimpleName());

        try {
            session.createQuery(hql).executeUpdate();
            tx.commit();
        } catch (Exception exception) {
            rollback(tx);
            throw new EntityManagerProxyException(exception);
        } finally {
            close(session);
        }
    }

    public final void delete(Object obj) throws Exception {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (Exception e) {
            rollback(tx);
            throw new Exception(e);
        } finally {
            close(session);
        }
    }

    /**
     * Find entity by id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public final Object find(Long id) throws Exception {
        Object obj;
        try {
            session = getSession();
            obj = session.load(entityClass, id);
        } catch (Exception e) {
            throw new EntityManagerProxyException(e);
        }

        return obj;
    }

    protected final void startOperation() {
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
