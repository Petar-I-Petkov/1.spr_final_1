package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "documents")
public class DocumentEntity extends BaseEntity {

    private String documentName;
    private List<ParagraphEntity> paragraphs;

    public DocumentEntity() {
    }

    @Column(name = "document_name", unique = true, nullable = false)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }



    @OneToMany(mappedBy = "document", targetEntity = ParagraphEntity.class)
    public List<ParagraphEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }
}