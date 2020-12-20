package Model;

import Exceptions.ExceedBuilderCapacityException;

import java.util.ArrayList;

public class Builder extends AbstractActor {
    private ArrayList<Car> availableCars = new ArrayList<>();
    private int maxCapacity;
    private int usedCapacity = 0;

    /**
     * Default constructor
     */
    public Builder() {
        super();
    }

    public Builder(String name, int maxCapacity) {
        super(name);
        this.maxCapacity = maxCapacity;
    }

    public boolean isSupportedCar(Car car) throws ExceedBuilderCapacityException {
        // search in cars list
        this.checkCapacity();
        for(int i = 0 ; i < this.availableCars.size() ; i++) {
        	if(car.getModelName() == this.availableCars.get(i).getModelName()) {
        	    this.incrementUsedCapacity();
                return true;
            }
        }
    	return false;
    }

    public void addCar(Car car) {
        this.availableCars.add(car);
    }

    public ArrayList<Car> getAvailableCars() {
        return availableCars;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void incrementUsedCapacity() throws ExceedBuilderCapacityException {
        this.checkCapacity();
        this.usedCapacity += 1;
    }

    public void decrementUsedCapacity() {
        this.usedCapacity -= 1;
    }

    protected void checkCapacity() throws ExceedBuilderCapacityException {
        if (usedCapacity == maxCapacity) {
            throw new ExceedBuilderCapacityException();
        }
    }

    /**
     * List toString
     * @param list
     * @return
     */
    public String toString(boolean list) {
        String carsString = ", ";
        for (Car car: availableCars) {
            carsString += car.getModelName() + " / ";
        }
        return this.getName() + carsString;
    }
}
