package Model;

import Model.Car.Car;

public class Order implements Entity{

    private int id;

    private enum status{
        done,
        processing,
        cancelled,
    };

    private Car car;

    private Builder builder;

    public Order() {
    }

    @Override
    public String toString() {
        return id +
                ", " + car +
                ", " + builder;
    }
}
