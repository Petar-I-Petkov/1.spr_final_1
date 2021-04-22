package com.petkov.spr_final_1.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


//    @Id
//    @GeneratedValue(generator = "uuid-string")
//    @GenericGenerator(
//            name = "uuid-string",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )