package com.petkov.spr_final_1.model.binding;

import javax.validation.constraints.*;

public class ChapterAddBindingModel {
    private Integer ataChapter;
    private String name;

    public ChapterAddBindingModel() {
    }

    //todo - ChapterAddBindingModel - add validations
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
    @Size(min = 5, message = "Chapter name length must be at least 5 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
