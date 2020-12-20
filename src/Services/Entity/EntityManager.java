package Services.Entity;

import Framework.Service;

import java.util.ArrayList;

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

    // adds entity to our collection
    public void add(Entity item) {
        item.setId(entityArrayList.size() + 1);
        entityArrayList.add(item);
    }

    public ArrayList<Entity> getAll() {
        return entityArrayList;
    }

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
