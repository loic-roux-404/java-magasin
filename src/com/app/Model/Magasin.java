package com.app.Model;

import com.app.Services.Entity.Entity;

import java.util.ArrayList;

public class Magasin implements Entity {
    private int id; // Numero
    private String phoneNumber;
    private String address;
    private int postalCode;
    private ArrayList<Article> availableArticles = new ArrayList<>();

    public Magasin() {}

    public Magasin(String phoneNumber, String address, int postalCode) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
    }

    public void addArticle(Article article) {
        this.availableArticles.add(article);
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
        return Integer.toString(id) + " / " + address;
    }

    @Override
    public String toString(boolean list) {
        return id
            + ", "
            + phoneNumber
            + ", "
            + address
            + ", "
            + postalCode
            + ", "
            + availableArticles();
    }

    protected String availableArticles() {
        String ref = "";

        for (Article p: availableArticles) {
            ref += p.intitule + "(" + p.technicalId + ")"; // TODO add p.technicalId instead of id
        };

        return ref;
    }
}
