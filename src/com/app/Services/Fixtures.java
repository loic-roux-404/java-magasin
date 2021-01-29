package com.app.Services;

import com.app.Framework.Service;
import com.app.Services.Entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Fixtures implements Service {
    public static String DEMO_TEXT_OFF = "Désactiver la démo";
    public static String DEMO_TEXT_ON = "Activer la démo";
    public boolean ENABLE = false;
    private boolean loaded = false;

    private HashMap<String, ArrayList<Entity>> map = new HashMap<>();
/*
    public Fixtures() {
        this.map.put(Builder.class.getSimpleName(), this.getFakeBuilders());
        this.map.put(Client.class.getSimpleName(), this.getFakeClients());
        this.map.put(Car.class.getSimpleName(), this.getFakeCars());
        load();
    }

    public void bootFixtures(Registery registery) throws ServiceRegisteryException {
        ENABLE = !ENABLE;
        EntityManager builderManager = (EntityManager) registery.get(Builder.class.getSimpleName());
        EntityManager carManager = (EntityManager) registery.get(Car.class.getSimpleName());
        EntityManager clientManager = (EntityManager) registery.get(Client.class.getSimpleName());

        if (ENABLE == false) {
            managerRemoveFixtures(builderManager);
            managerRemoveFixtures(carManager);
            managerRemoveFixtures(clientManager);
            return;
        };

        managerSetFixtures(builderManager);
        managerSetFixtures(carManager);
        managerSetFixtures(clientManager);
    }

    protected void managerSetFixtures(EntityManager manager) {
        manager.setEntityArrayList(this.map.get(manager.getEntityClass().getSimpleName()));
    }

    protected void managerRemoveFixtures(EntityManager manager) {
        manager.setEntityArrayList(new ArrayList<>());
    }

    private ArrayList<Entity> getFakeBuilders() {
        ArrayList<Entity> arr = new ArrayList<>();

        Builder b1 = new Builder("TMAX Y", 2);
        b1.addCar((Car) this.getFakeCars().get(0));
        b1.addCar((Car) this.getFakeCars().get(2));
        b1.addCar((Car) this.getFakeCars().get(3));

        Builder b2 = new Builder("Jean Zouave SARL", 1);
        b2.addCar((Car) this.getFakeCars().get(3));
        b2.addCar((Car) this.getFakeCars().get(4));

        Builder b3 = new Builder("ADIBOU SAS", 4);
        b3.addCar((Car) this.getFakeCars().get(0));
        Builder b4 = new Builder("Carrosserie MAXIMATOR", 3);
        b4.addCar((Car) this.getFakeCars().get(0));

        Builder b5 = new Builder("86 Construct", 1);

        arr.add(b1);
        arr.add(b2);
        arr.add(b3);
        arr.add(b4);
        arr.add(b5);

        return arr;
    }

    private ArrayList<Entity> getFakeCars() {
        ArrayList<Entity> arr = new ArrayList<>();

        Car c1 = new Car("Renault", "Laguna", 1000, 2000, 18000.49);
        Car c2 = new Car("Ferarri", "458 Italia", 1000, 1100,1000000.00);
        Car c3 = new Car("Ford", "Fiesta", 800, 3000, 12000.00);
        Car c4 = new Car("Mercedes", "AMG", 1000, 1200, 67000.00);
        Car c5 = new Car("Renault", "Twingo", 200, 500, 11000.00);

        arr.add(c1);
        arr.add(c2);
        arr.add(c3);
        arr.add(c4);
        arr.add(c5);

        return arr;
    }

    private ArrayList<Entity> getFakeClients() {
        ArrayList<Entity> arr = new ArrayList<>();

        Client c1 = new Client("Honoré", "de Balzac");
        Client c2 = new Client("Steve", "Jobs");
        Client c3 = new Client("Brad", "Pitre");
        Client c4 = new Client("Jean", "Chie");
        Client c5 = new Client("Sarah", "sciste");

        arr.add(c1);
        arr.add(c2);
        arr.add(c3);
        arr.add(c4);
        arr.add(c5);

        return arr;
    }
*/
    public String demoText() {
        return ENABLE ? DEMO_TEXT_OFF : DEMO_TEXT_ON;
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
