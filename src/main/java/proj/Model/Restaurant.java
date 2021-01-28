package main.java.proj.Model;

import main.java.proj.Services.Entity.Entity;

import java.util.ArrayList;

public class Restaurant implements Entity {
    private int id; // Numero
    private String phoneNumber;
    private String address;
    private ArrayList<Product> availableProducts = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addRestaurant(Product restau) {
        this.availableProducts.add(restau);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Entity setId(int id) {
        this.id = id;

        return this;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public String toString(boolean list) {
        return id
            + ", "
            + phoneNumber
            + ", "
            + availableProducts();
    }

    protected String availableProducts() {
        String ref = "";

        for (Product p: availableProducts) {
            ref += p.type + "(" + p.id + ")";
        };

        return ref;
    }
}
