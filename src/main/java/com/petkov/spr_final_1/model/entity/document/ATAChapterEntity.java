package com.petkov.spr_final_1.model.entity.document;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "chapters")
public class ATAChapterEntity extends BaseEntity {

    private Integer ataCode;
    private String name;
    private List<ATASubChapterEntity> ataSections;
    private List<ArticleEntity> articles;

    public ATAChapterEntity() {
    }

    @Column(name = "ata_code", unique = true, nullable = false)
    public Integer getAtaCode() {
        return ataCode;
    }

    public void setAtaCode(Integer ataCode) {
        this.ataCode = ataCode;
    }

    @Column(name = "chapter_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "ataChapterRef", targetEntity = ATASubChapterEntity.class)
    public List<ATASubChapterEntity> getAtaSections() {
        return ataSections;
    }

    public void setAtaSections(List<ATASubChapterEntity> ataSections) {
        this.ataSections = ataSections;
    }

    @OneToMany(mappedBy = "chapter", targetEntity = ArticleEntity.class)
    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
