package com.app.Model;

import com.app.Services.IEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity, Dated {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public IEntity setId(Long id) {
        this.id = id;

        return this;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public Dated setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public Dated setUpdatedAt(Date createdAt) {
        this.updatedAt = createdAt;

        return this;
    }
}
