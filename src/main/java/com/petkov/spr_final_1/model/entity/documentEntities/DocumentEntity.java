package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class DocumentEntity extends BaseEntity {

    private String name;

    public DocumentEntity() {
    }

    @Column(name = "document_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
