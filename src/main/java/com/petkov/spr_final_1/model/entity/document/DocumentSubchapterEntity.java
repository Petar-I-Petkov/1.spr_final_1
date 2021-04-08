package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "document_sub_chapters")
public class DocumentSubchapterEntity extends BaseEntity {

    private String docSubchapterName;
    private DocumentEntity document;
    private List<ArticleEntity> articles;

    public DocumentSubchapterEntity() {
    }

    @Column(name = "name", unique = false, nullable = false)
    public String getDocSubchapterName() {
        return docSubchapterName;
    }

    public void setDocSubchapterName(String docSubchapterName) {
        this.docSubchapterName = docSubchapterName;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document", referencedColumnName = "id")
    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    @OneToMany(mappedBy = "documentSubchapter", targetEntity = ArticleEntity.class)
    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
