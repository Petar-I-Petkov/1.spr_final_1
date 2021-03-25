package com.petkov.spr_final_1.model.service;

import com.petkov.spr_final_1.model.entity.documentEntities.SectionEntity;

import java.util.ArrayList;
import java.util.List;

public class ChapterServiceModel extends BaseServiceModel{

    private Integer ataChapter;
    private String name;
    private List<SectionEntity> sections = new ArrayList<>();

    public ChapterServiceModel() {
    }

    //todo ChapterServiceModel - add getter validations
    public Integer getAtaChapter() {
        return ataChapter;
    }

    public void setAtaChapter(Integer ataChapter) {
        this.ataChapter = ataChapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }
}
