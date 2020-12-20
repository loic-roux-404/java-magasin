package Model;

import Services.Entity.Entity;

public class Car implements Entity {

    private int id;
    private String modelName;
    private String brandName;
    private Integer length;
    private Integer weight;
    private Double price;

    /**
     * Default constructor
     */
    public Car() {
    }

    public Car(String brandName, String modelName, Integer length, Integer weight, Double price) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.length = length;
        this.weight = weight;
        this.price = price;
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
    
    public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
            + ", " + modelName;
    }

    @Override
    public String toString(boolean list) {
        return brandName
            + ", " + modelName
            + ", " + length
            + ", " + weight
            + ", " + price;
    }
}
