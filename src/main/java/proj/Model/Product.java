package main.java.proj.Model;

import main.java.proj.Services.Entity.Entity;

import java.util.Date;

public class Product implements Entity, Dated {
    int id; // reference
    String type;
    Date expiration;
    Date createdAt; // Date fabrication
    Date updatedAt;

    public Product() {}

    public Product(String type, Date expiration) {
        this.type = type;
        this.expiration = expiration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return id + Entity.COMMA + type;
    }

    @Override
    public String toString(boolean list) {
        return id + Entity.COMMA +
            type + Entity.COMMA +
            expiration + Entity.COMMA +
            createdAt + Entity.COMMA +
            Entity.COMMA + updatedAt;
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
}
