package Model;

import Model.Car.Car;

public class Order implements Entity{

    private int id;

    static enum statuses{
        DONE,
        PROCESSING,
        CANCELLED,
    };

    private String status;

    private Car car;

    private Builder builder;

    private Client client;

    public Order() {
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
