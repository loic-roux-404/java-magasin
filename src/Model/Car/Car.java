package Model.Car;

public class Car {

    /**
     * Default constructor
     */
    public Car() {}

    private CarBrand carBrand;

    private CarModel type;

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarModel getType() {
        return type;
    }

    public void setType(CarModel type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return carBrand.getName() +
                ", " + type.getName();
    }

    public void Operation1() {
        // TODO implement here
    }
}