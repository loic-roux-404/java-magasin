package com.app.Model;

import com.app.Services.Entity.Entity;

/**
 * Class used to reuse simple properties for
 * Client and Builders which are the two actors of the app
 */
public abstract class AbstractActor implements Entity {
    private int id;
    private String name;

    /**
     * Default
     */
    public AbstractActor() {}

    public AbstractActor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    /**
     * Java default toString
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }
}
