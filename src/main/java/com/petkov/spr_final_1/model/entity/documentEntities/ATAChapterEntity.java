package com.petkov.spr_final_1.model.entity.documentEntities;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "chapters")
public class ATAChapterEntity extends BaseEntity {

    private Integer ataChapter;
    private String name;
    private List<ATASubChapterEntity> ataSections;

    public ATAChapterEntity() {
    }

    @Column(name = "ata_chapter", unique = true, nullable = false)
    public Integer getAtaChapter() {
        return ataChapter;
    }

    public void setAtaChapter(Integer ataChapter) {
        this.ataChapter = ataChapter;
    }


    @Column(name = "chapter_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "ataChapterRef", targetEntity = ATASubChapterEntity.class)
    public List<ATASubChapterEntity> getSections() {
        return ataSections;
    }

    public void setSections(List<ATASubChapterEntity> ataSections) {
        this.ataSections = ataSections;
    }
}
