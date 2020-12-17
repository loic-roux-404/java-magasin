package Services.Entity;

import Framework.Service;

import java.util.ArrayList;

public class EntityManager implements Service {
    private boolean loaded;

    private ArrayList<Entity> entityArrayList;

    protected Entity managedEn;

    public EntityManager(Class entityClass) {
        entityArrayList = new ArrayList<>();
        this.loadEntityInstance(entityClass);
        this.load();
    }

    void loadEntityInstance(Class entityClass) {
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

    @Override
    public void load() {
        loaded = true;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }
}
