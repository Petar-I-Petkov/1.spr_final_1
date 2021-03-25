package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
public class SectionEntity extends BaseEntity {

    private String ataSection;
    private String name;
    private List<ParagraphEntity> subSections;
    private ChapterEntity chapter;

    public SectionEntity() {
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
        return subSections;
    }

    public void setSubSections(List<ParagraphEntity> subSections) {
        this.subSections = subSections;
    }

    @ManyToOne
    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ChapterEntity chapter) {
        this.chapter = chapter;
    }
}
