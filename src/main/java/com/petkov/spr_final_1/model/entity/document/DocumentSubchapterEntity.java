package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "document_sub_chapters")
public class DocumentSubchapterEntity extends BaseEntity {

    private String docSubchapterName;
    private DocumentEntity document;

    public DocumentSubchapterEntity() {
    }

    @Column(name = "name", unique = false, nullable = false)
    public String getDocSubchapterName() {
        return docSubchapterName;
    }

    public void setDocSubchapterName(String docSubchapterName) {
        this.docSubchapterName = docSubchapterName;
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
