package Model.Car;

/**
 * 
 */
public class CarModel {

    /**
     * Default constructor
     */
    public CarModel() {
    }

    private String name;

    private String length;

    private double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}