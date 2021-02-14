package com.app.Model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Magasin")
public class Magasin extends AbstractEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private int postalCode;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(mappedBy = "magasins", fetch = FetchType.LAZY)
    private Set<Article> availableArticles = new HashSet<>();

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

    public Set<Article> getAvailableArticles() {
        return availableArticles;
    }

    public void setAvailableArticles(Set<Article> availableArticles) {
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
            + showAvailableArticles()
        ;
    }

    protected String showAvailableArticles() {
        String ref = "";

        for (Article p: getAvailableArticles()) {
            ref += p.getIntitule() + "(" + p.getId() + ")";
        };

        return ref;
    }
}
