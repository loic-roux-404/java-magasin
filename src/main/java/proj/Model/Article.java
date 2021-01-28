package main.java.proj.Model;

import main.java.proj.Services.Entity.Entity;

import java.util.Date;

public class Article implements Entity {
	int technicalId;
    long reference; // reference
    String intitule;
    float prixHT;
    int quantiteEnStock;

    public Article() {}

    public Article(long reference, String intitule, float prixHT,int quantiteEnStock) {
    	this.reference = reference;
    	this.intitule = intitule;
    	this.prixHT = prixHT;
    	this.quantiteEnStock = quantiteEnStock;
    }
    
    public long getReference() {
    	return reference;
    }
    
    public void approvisionner(int qte) {
    	quantiteEnStock+=qte;
    }
    
    public boolean vendre(int qte) {
    	if(qte > quantiteEnStock) {
    		return false;
    	}else {
    		quantiteEnStock-=qte;
    		return true;
    	}
    }

    public float prixHT() {
    	return prixHT;
    }
    
    public float prixTTC() {
    	return (prixHT*(1+20/100));
    }

    @Override
    public String toString() {
        return reference + Entity.COMMA + intitule;
    }

    @Override
    public String toString(boolean list) {
        return reference + Entity.COMMA +
        		intitule + Entity.COMMA +
        		prixHT + Entity.COMMA +
        		prixTTC() + Entity.COMMA +
        		quantiteEnStock + Entity.COMMA;
    }

    @Override
    public int getId() {
        return technicalId;
    }

    @Override
    public Entity setId(int id) {
        this.technicalId = id;
        return this;
    }
}
