package Model;

import Services.Entity.Entity;

public class Car implements Entity {

    private int id;
    private String modelName;
    private String brandName;
    private double length;
    private double weight;

    /**
     * Default constructor
     */
    public Car() {}

    public Car(String brandName, String modelName, double length, double weight) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.length = length;
        this.weight = weight;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Car setId(int id) {
        this.id = id;

        return this;
    }

    @Override
    public String toString() {
        return brandName
                + ", " + modelName
                + ", " + length
                + ", " + weight;
    }

    public void Operation1() {
        // TODO implement here
    }
}
