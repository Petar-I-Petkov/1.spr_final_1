package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "document_sub_chapters")
@Access(AccessType.PROPERTY)
public class DocumentSubchapterEntity extends BaseEntity {

    private String name;
    private DocumentEntity document;

    public DocumentSubchapterEntity() {
    }

    @Column(name = "name", unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String docSubchapterName) {
        this.name = docSubchapterName;
    }

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "id")
    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

}
