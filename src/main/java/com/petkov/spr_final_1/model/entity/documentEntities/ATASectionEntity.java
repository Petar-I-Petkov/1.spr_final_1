package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
public class ATASectionEntity extends BaseEntity {

    private String ataSection;
    private String name;
    private List<ParagraphEntity> paragraphs;
    private ATAChapterEntity ataChapter;

    public ATASectionEntity() {
    }

    @Column(name = "ata_section", unique = true, nullable = false)
    public String getAtaSection() {
        return ataSection;
    }

    public void setAtaSection(String ataSection) {
        this.ataSection = ataSection;
    }

    @Column(name = "section_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    public List<ParagraphEntity> getSubSections() {
        return paragraphs;
    }

    public void setSubSections(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @ManyToOne
    public ATAChapterEntity getChapter() {
        return ataChapter;
    }

    public void setChapter(ATAChapterEntity ataChapter) {
        this.ataChapter = ataChapter;
    }
}
