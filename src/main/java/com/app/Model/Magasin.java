package com.app.Model;

import com.app.Services.Entity.IEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "magasin")
public class Magasin implements IEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id; // Numero

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private int postalCode;

    @ManyToMany(targetEntity = Article.class, mappedBy = "magasin")
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
    public int getId() {
        return id;
    }

    @Override
    public IEntity setId(int id) {
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
            ref += p.intitule + "(" + p.id + ")"; // TODO add p.technicalId instead of id
        };

        return ref;
    }
}
