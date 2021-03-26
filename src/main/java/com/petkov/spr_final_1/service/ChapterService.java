package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ChapterServiceModel;

import java.util.List;

public interface ChapterService {
    void initSeedChapters();

    void addChapterToDB(ChapterServiceModel chapterServiceModel);

    List<String> listAllChaptersAtaAndNameOrderByAtaDesc();
}
