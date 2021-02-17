package com.app.Model;

import com.app.Services.IEntity;

import javax.persistence.MappedSuperclass;

/**
 * Class used to reuse simple properties for
 * Client and Builders which are the two actors of the app
 */
@MappedSuperclass
public abstract class AbstractActor extends AbstractEntity {
    private Long id;
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
    public Long getId() {
        return id;
    }

    @Override
    public IEntity setId(Long id) {
        this.id = id;

        return this;
    }

    /**
     * Java default toString
     * @return string
     */
    @Override
    public String toString() {
        return this.name;
    }
}
