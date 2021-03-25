package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ChapterServiceModel;

public interface ChapterService {
    void initSeedChapters();

    void addChapterToDB(ChapterServiceModel chapterServiceModel);
}
