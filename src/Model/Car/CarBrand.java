package Model.Car;

public class CarBrand {

    private String name;

    /**
     * Default constructor
     */
    public CarBrand() {}
    
    public CarBrand(String name) {
	this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
