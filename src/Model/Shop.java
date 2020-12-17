package Model;

import Services.Entity.Entity;

/**
 * Shop is the same for all, juste there is this car shop brand in multiple cities
 * They are sharing this soft so only address
 */
public class Shop implements Entity {
    /**
     *
     */
    public String address;

    @Override
    public String toString() {
        return address;
    }

    @Override
    public Entity factory(String[] dbData) {
        return new Shop();
    }
}
