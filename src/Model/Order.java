package Model;

import Model.Car.Car;

public class Order implements Entity{

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


    public Order(int id, Client client, Car car) {
    	this.id = id;
        this.client = client;
        this.car = car;
    }

    // TODO use serialization
    @Override
    public String toString() {
        return id +
                ", " + status +
                ", Car{" + car.toString() + "}" +
                ", Client{" + car.toString() + "}" +
                ", Builder{" + builder.toString() + "}";
    }
}
