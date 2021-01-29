package com.app.Services.Entity;

import com.app.Framework.Service;
import com.app.Model.Dated;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class to manage entities and store them in list
 */
public class EntityManager implements Service {
    private boolean loaded;

    private ArrayList<IEntity> IEntityArrayList;

    protected IEntity managedEn;
    private Class entityClass;

    public EntityManager(Class entityClass) {
        this.entityClass = entityClass;
        IEntityArrayList = new ArrayList<>();
        this.loadEntityInstance();
        this.load();
    }

    /**
     * Use reflection to create an empty entity and check definition
     * is compliant with this manager
     */
    void loadEntityInstance() {
        try {
            managedEn = (IEntity) entityClass.getDeclaredConstructor().newInstance();
            // managedEn.ge
        } catch (Exception e) {
            System.err.println("This entity definition has errors " + entityClass.getName());
            System.err.println("Verify methods and empty constructor usage");
            e.printStackTrace();
        }

    }

    /**
     * Adds entity to our collection
     *
     * @param item
     */
    public void add(IEntity item) {
        item.setId(IEntityArrayList.size() + 1);
        if (item instanceof Dated) {
            ((Dated) item).setUpdatedAt(new Date());
            if (((Dated) item).getCreatedAt() == null) {
                ((Dated) item).setCreatedAt(new Date());
            }
        }
        IEntityArrayList.add(item);
    }

    /**
     * Get all entities for managed entity (ex Car)
     * @return
     */
    public ArrayList<IEntity> getAll() {
        return IEntityArrayList;
    }

    /**
     * Get one entity by his index in list
     * @param index
     * @return
     */
    public IEntity getById(int index) {
        return IEntityArrayList.get(index);
    }

    /**
     * Used to set fixtures
     * @param IEntityArrayList
     */
    public void setEntityArrayList(ArrayList<IEntity> IEntityArrayList) {
        this.IEntityArrayList = IEntityArrayList;
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
