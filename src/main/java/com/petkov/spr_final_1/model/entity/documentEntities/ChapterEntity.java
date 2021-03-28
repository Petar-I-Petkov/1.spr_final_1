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

    private Integer ataCode;
    private String name;
    private List<SubChapterEntity> ataSections;

    public ChapterEntity() {
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

    @OneToMany(mappedBy = "ataChapterRef", targetEntity = SubChapterEntity.class)
    public List<SubChapterEntity> getAtaSections() {
        return ataSections;
    }

    public void setAtaSections(List<SubChapterEntity> ataSections) {
        this.ataSections = ataSections;
    }
}
