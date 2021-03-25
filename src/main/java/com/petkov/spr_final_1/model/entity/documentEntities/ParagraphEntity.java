package com.petkov.spr_final_1.model.entity.documentEntities;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "paragpraphs")
public class
ParagraphEntity extends BaseEntity {

    private String title;
    private String documentReference;
    private String documentRevision;
    private LocalDateTime documentDate;
    private String description;
    // todo - add picture holders
    private ATASectionEntity ataSection;
    private DocumentEntity document;

    public ParagraphEntity() {
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "document", unique = false, nullable = false)
    public String getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }

    @Column(name = "revision", unique = false, nullable = true)
    public String getDocumentRevision() {
        return documentRevision;
    }

    public void setDocumentRevision(String documentRevision) {
        this.documentRevision = documentRevision;
    }

    @Column(name = "document_date", unique = false, nullable = true)
    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

    @Column(name = "description", unique = false, nullable = false , columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public ATASectionEntity getAtaSection() {
        return ataSection;
    }

    public void setAtaSection(ATASectionEntity ataSection) {
        this.ataSection = ataSection;
    }

    @ManyToOne
    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }
}
