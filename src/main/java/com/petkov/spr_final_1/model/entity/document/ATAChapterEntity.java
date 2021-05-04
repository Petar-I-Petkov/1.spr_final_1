package com.petkov.spr_final_1.model.entity.document;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ata_chapter")
@Access(AccessType.PROPERTY)
public class ATAChapterEntity extends AbstractReference {

    private Integer ataCode;

    public ATAChapterEntity() {
    }

    @Column(name = "ata_code", unique = true)
    public Integer getAtaCode() {
        return ataCode;
    }

    public void setAtaCode(Integer ataCode) {
        this.ataCode = ataCode;
    }

}
