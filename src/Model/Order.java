package Model;

import Services.Entity.Entity;

public class Order implements Entity {

    private int id;
    private statuses status;
    private Car car;
    private Builder builder;
    private Client client;

    static enum statuses{
    	PENDING,
    	PROCESSING,
        DONE,
        CANCELLED,
    };

    /**
     * Default constructor, used by entityManager
     */
    public Order() {

    }

    public Order(int id, Client client, Car car) {
    	this.id = id;
        this.client = client;
        this.car = car;
        this.builder = new Builder();
        status = statuses.valueOf("PENDING");
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Order setId(int id) {
        this.id = id;

        return this;
    }

    @Override
    public String toString() {
        return id +
                ", " + status +
                ", " + client.toString() +
                ", " + car.getBrandName() +
                ", " + car.getModelName() +
                ", " + builder.toString();
    }
}
