package Model.Car;

public class CarBrand {

    /**
     * Default constructor
     */
    public CarBrand() {
    }

    
    
    public CarBrand(String name) {
		this.name = name;
	}



	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}