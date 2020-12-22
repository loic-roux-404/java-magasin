package Services.Entity;

import Framework.Service;
import Model.Dated;

import java.util.ArrayList;

/**
 * Class to manage entities and store them in list
 */
public class EntityManager implements Service {
    private boolean loaded;

    private ArrayList<Entity> entityArrayList;

    protected Entity managedEn;
    private Class entityClass;

    public EntityManager(Class entityClass) {
        this.entityClass = entityClass;
        entityArrayList = new ArrayList<>();
        this.loadEntityInstance();
        this.load();
    }

    /**
     * Use reflection to create an empty entity and check definition
     * is compliant with this manager
     */
    void loadEntityInstance() {
        try {
            managedEn = (Entity) entityClass.getDeclaredConstructor().newInstance();
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
    public void add(Entity item) {
        item.setId(entityArrayList.size() + 1);
        if (item instanceof Dated) {
            ((Dated) item).setUpdatedAt();
            if (((Dated) item).getCreatedAt() == null) {
                ((Dated) item).setCreatedAt();
            }
        }
        entityArrayList.add(item);
    }

    /**
     * Get all entities for managed entity (ex Car)
     * @return
     */
    public ArrayList<Entity> getAll() {
        return entityArrayList;
    }

    /**
     * Get one entity by his index in list
     * @param index
     * @return
     */
    public Entity getById(int index) {
        return entityArrayList.get(index);
    }

    /**
     * Used to set fixtures
     * @param entityArrayList
     */
    public void setEntityArrayList(ArrayList<Entity> entityArrayList) {
        this.entityArrayList = entityArrayList;
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
