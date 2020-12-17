package Model;

import Model.Car.Car;
import Services.Entity.Entity;

public class Order implements Entity {

    private int id;
    private String status;
    private Car car;
    private Builder builder;
    private Client client;

    static enum statuses{
        DONE,
        PROCESSING,
        CANCELLED,
    };

    public Order() {

    }

    public Order(int id, Client client, Car car) {
    	this.id = id;
        this.client = client;
        this.car = car;
    }

    @Override
    public String toString() {
        return id +
                ", " + status +
                ", " + car.toString() +
                ", " + car.toString() +
                ", " + builder.toString();
    }
}
