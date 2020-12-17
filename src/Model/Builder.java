package Model;

import Model.Car.Car;
import Services.Entity.Entity;

import java.util.ArrayList;

public class Builder implements Entity {

    /**
     * Default constructor
     */
    public Builder() { }

    private String name;

    private ArrayList<Car> availableCars;

    public void orderCreation() {
        // TODO implement here
        // Check if car is in list of supported
        if (!this.isSupportedCar()) {
            this.nextBuilder();
        }
        // Call to car factory
    }

    public Builder nextBuilder() {
        return new Builder();
    };

    protected boolean isSupportedCar() {
        // search in cars list
        return false;
    }

    public void setAvailableCars(ArrayList<Car> availableCars) {
        this.availableCars = availableCars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Car> getAvailableCars() {
        return availableCars;
    }

    @Override
    public String toString() {
        // TODO Verify if data is comma separated list
        return name + ", " + availableCars.toString();
    }

    @Override
    public Entity factory(String[] dbData) {
        return new Builder();
    }
}
