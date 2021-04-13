package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "documents")
public class DocumentEntity extends BaseEntity {

    private String name;

    public DocumentEntity() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
