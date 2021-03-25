package com.petkov.spr_final_1.model.service;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;

public class ChapterServiceModel extends BaseServiceModel{

    @Expose
    private Integer ataChapter;
    @Expose
    private String name;

    public ChapterServiceModel() {
    }

    @NotNull(message = "ATA code cannot be empty.")
    @Min(value = 0, message = "ATA chapter minimum is 00.")
    @Max(value = 100,  message = "ATA chapter maximum is 100.")
    public Integer getAtaChapter() {
        return ataChapter;
    }

    public void setAtaChapter(Integer ataChapter) {
        this.ataChapter = ataChapter;
    }


    @NotBlank(message = "Chapter name cannot be empty.")
    @Size(min = 3, message = "Chapter name length must be at least 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
