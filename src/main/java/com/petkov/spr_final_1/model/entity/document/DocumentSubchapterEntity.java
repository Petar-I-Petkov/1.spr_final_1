package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("document_subchapter")
@Access(AccessType.PROPERTY)
public class DocumentSubchapterEntity extends AbstractReference {

    private DocumentEntity document;

    public DocumentSubchapterEntity() {
    }

    @ManyToOne
    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

}
