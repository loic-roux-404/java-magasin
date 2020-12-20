package Model;

import Services.Entity.Entity;

import java.util.ArrayList;

public class Builder implements Entity {
    private int id;
    private String name;

    /**
     * Default constructor
     */
    public Builder() {
    }

    public Builder(String name) {
        this.name = name;
    }

    private ArrayList<Car> availableCars = new ArrayList<>();

    public void orderCreation() {
        // TODO implement here
        // Check if car is in list of supported

        // Call to car factory
    }


    public boolean isSupportedCar(Car car) {
        // search in cars list
    	for(int i = 0 ; i < this.availableCars.size() ; i++) {
        	if(car.getModelName() == this.availableCars.get(i).getModelName())
        		return true;
        }
    	return false;
        
    }

    public void addCar(Car car) {
        this.availableCars.add(car);
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
    public int getId() {
        return id;
    }

    @Override
    public Builder setId(int id) {
        this.id = id;

        return this;
    }

    /**
     * List toString
     * @param list
     * @return
     */
    public String toString(boolean list) {
        String carsString = ", ";
        for (Car car: availableCars) {
            carsString += car.getModelName() + ", ";
        }
        return name + carsString;
    }

    /**
     * Java default toString
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
}
