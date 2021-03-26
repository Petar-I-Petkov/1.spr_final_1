package com.petkov.spr_final_1.model.service;

import javax.validation.constraints.*;

public class ATASubChapterServiceModel {

    private Integer ataSubCode;
    private String subchapterName;
    private String ataChapterRef;

    public ATASubChapterServiceModel() {
    }

    @NotNull(message = "ATA code cannot be empty.")
    @Min(value = 0, message = "ATA chapter minimum is 00.")
    @Max(value = 100,  message = "ATA chapter maximum is 100.")
    public Integer getAtaSubCode() {
        return ataSubCode;
    }

    public void setAtaSubCode(Integer ataSubCode) {
        this.ataSubCode = ataSubCode;
    }

    @NotBlank(message = "SubChapter name cannot be empty.")
    @Size(min = 3, message = "SubChapter name length must be at least 3 characters.")
    public String getSubchapterName() {
        return subchapterName;
    }

    public void setSubchapterName(String subchapterName) {
        this.subchapterName = subchapterName;
    }

    @NotBlank(message = "ATA Chapter reference cannot be empty.")
    @Size(min = 2, message = "ATA Chapter reference length must be at least 3 characters.")
    public String getAtaChapterRef() {
        return ataChapterRef;
    }

    public void setAtaChapterRef(String ataChapterRef) {
        this.ataChapterRef = ataChapterRef;
    }
}
