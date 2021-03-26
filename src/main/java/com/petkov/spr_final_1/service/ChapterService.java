package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ATAChapterServiceModel;

import java.util.List;

public interface ChapterService {
    void initSeedChapters();

    void addChapterToDB(ATAChapterServiceModel ataChapterServiceModel);

    List<String> listAllChaptersAtaAndNameOrderByAtaDesc();
}
