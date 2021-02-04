package com.app.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Article")
public class Article extends AbstractEntity {

    @Column(name = "reference")
    long reference; // reference

    @Column(name = "intitule")
    String intitule;

    @Column(name = "prixHT")
    float prixHT;

    @Column(name = "stock")
    int stock;

    @Column(name = "TVA")
    float TVA = 1.2f;

    @ManyToMany()
    @JoinTable(name = "article_magasin",
            joinColumns = { @JoinColumn(name = "fk_article") },
            inverseJoinColumns = { @JoinColumn(name = "fk_magasin") })
    List<Magasin> magasins = new ArrayList<>();

    public Article() {}

    public Article(long reference, String intitule, float prixHT,int stock) {
    	this.reference = reference;
    	this.intitule = intitule;
    	this.prixHT = prixHT;
    	this.stock = stock;
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int quantiteEnStock) {
        this.stock = quantiteEnStock;
    }

    public float getTVA() {
        return TVA;
    }

    public void setTVA(float TVA) {
        this.TVA = TVA;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    // Entity logical actions

    public void approvisionner(int qte) {
    	stock +=qte;
    }
    
    public boolean vendre(int qte) {
    	if(qte > stock) {
    		return false;
    	}else {
    		stock -=qte;
    		return true;
    	}
    }

    public float prixHT() {
    	return prixHT;
    }
    
    public float prixTTC() {
    	float ttc = Math.round((prixHT*TVA) * 100) / 100;
    	return ttc;
    }

    @Override
    public String toString() {
        return reference + ", " + intitule;
    }

    @Override
    public String toString(boolean list) {
        return
            getReference() + ", "
                + getIntitule() + ", "
                + getPrixHT() + ", "
                + prixTTC() + ", "
                + getStock()
            ;
    }
}
