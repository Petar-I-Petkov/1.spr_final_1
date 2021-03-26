package com.petkov.spr_final_1.model.entity.documentEntities;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
public class ATASubChapterEntity extends BaseEntity {

    private Integer ataSubCode;
    private String subchapterName;
    private List<ParagraphEntity> paragraphs;
    private ATAChapterEntity ataChapterRef;

    public ATASubChapterEntity() {
    }

    @Column(name = "ata_subcode", unique = true, nullable = false)
    public Integer getAtaSubCode() {
        return ataSubCode;
    }

    public void setAtaSubCode(Integer ataSubCode) {
        this.ataSubCode = ataSubCode;
    }

    @Column(name = "subchapter_name", unique = true, nullable = false)
    public String getSubchapterName() {
        return subchapterName;
    }

    public void setSubchapterName(String subchapterName) {
        this.subchapterName = subchapterName;
    }

    @OneToMany
    public List<ParagraphEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }


    @ManyToOne
    public ATAChapterEntity getAtaChapterRef() {
        return ataChapterRef;
    }

    public void setAtaChapterRef(ATAChapterEntity ataChapterRef) {
        this.ataChapterRef = ataChapterRef;
    }
}

