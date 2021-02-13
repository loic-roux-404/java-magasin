package com.app.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Magasin")
public class Magasin extends AbstractEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private int postalCode;

    @ManyToMany(targetEntity = Article.class, mappedBy = "magasins")
    private List<Article> availableArticles = new ArrayList<>();

    public Magasin() {}

    public Magasin(String phoneNumber, String address, int postalCode) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public List<Article> getAvailableArticles() {
        return availableArticles;
    }

    public void setAvailableArticles(List<Article> availableArticles) {
        this.availableArticles = availableArticles;
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
    public String toString() {
        return Long.toString(getId()) + " / " + address;
    }

    @Override
    public String toString(boolean list) {
        return getId()
            + ", "
            + getPhoneNumber()
            + ", "
            + getAddress()
            + ", "
            + getPostalCode()
            + ", "
            + availableArticles()
        ;
    }

    protected String availableArticles() {
        String ref = "";

        for (Article p: availableArticles) {
            ref += p.intitule + "(" + p.getId() + ")";
        };

        return ref;
    }
}
