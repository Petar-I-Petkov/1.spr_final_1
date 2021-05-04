package com.petkov.spr_final_1.model.entity.document;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "external_references")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="reference_type", discriminatorType = DiscriminatorType.STRING)
@Access(AccessType.PROPERTY)
public abstract class AbstractReference extends BaseEntity{

    private String name;

    public AbstractReference() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
