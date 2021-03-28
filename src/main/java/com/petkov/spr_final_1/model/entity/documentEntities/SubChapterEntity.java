package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sub_chapters")
public class SubChapterEntity extends BaseEntity {

    private Integer ataSubCode;
    private String subchapterName;
    private List<ParagraphEntity> paragraphs;
    private ChapterEntity ataChapterRef;

    public SubChapterEntity() {
    }

    @Column(name = "ata_subcode", unique = false, nullable = false)
    public Integer getAtaSubCode() {
        return ataSubCode;
    }

    public void setAtaSubCode(Integer ataSubCode) {
        this.ataSubCode = ataSubCode;
    }

    @Column(name = "subchapter_name", unique = false, nullable = false)
    public String getSubchapterName() {
        return subchapterName;
    }

    public void setSubchapterName(String subchapterName) {
        this.subchapterName = subchapterName;
    }

    @OneToMany(mappedBy = "ataSubChapter", targetEntity = ParagraphEntity.class)
    public List<ParagraphEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }


    @ManyToOne
    @JoinColumn(name = "ata_chapter", referencedColumnName = "id")
    public ChapterEntity getAtaChapterRef() {
        return ataChapterRef;
    }

    public void setAtaChapterRef(ChapterEntity ataChapterRef) {
        this.ataChapterRef = ataChapterRef;
    }
}
