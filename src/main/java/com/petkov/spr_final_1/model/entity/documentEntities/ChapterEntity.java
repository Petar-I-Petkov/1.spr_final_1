package com.petkov.spr_final_1.model.entity.documentEntities;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "chapters")
public class ChapterEntity extends BaseEntity {

    private Integer ataChapter;
    private String name;
    private List<SectionEntity> sections;

    public ChapterEntity() {
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

    @OneToMany
    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }
}
