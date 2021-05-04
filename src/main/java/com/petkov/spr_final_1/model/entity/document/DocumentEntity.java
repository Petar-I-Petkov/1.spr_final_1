package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("document")
@Access(AccessType.PROPERTY)
public class DocumentEntity extends AbstractReference {

    public DocumentEntity() {
    }

}
