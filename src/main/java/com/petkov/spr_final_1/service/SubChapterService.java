package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.model.service.SubChapterServiceModel;

public interface SubChapterService {

    void seedATASubchapterToDb(SubChapterServiceModel subChapterServiceModel);

    boolean subChapterCodeExists(int ataSubCode, int chapterRef);

    void initSeedSubchaptersFromJson();

    SubChapterServiceModel findByChapterAndSubchapterAta(int chapterRef, int ataSubCode);
}
