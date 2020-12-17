package Model;

import Services.Entity.Entity;

/**
 * Shop is the same for all, juste there is this car shop brand in multiple cities
 * They are sharing this soft so only address
 */
public class Shop implements Entity {
    private int id;
    private String address;

    /**
     * Default constructor, used by entityManager
     */
    public Shop() {}

    public Shop(int id, String address) {
        this.id = id;
        this.address = address;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Shop setId(int id) {
        this.id = id;

        return this;
    }

    @Override
    public String toString() {
        return address;
    }
}
