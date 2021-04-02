package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.model.view.ChapterViewModel;

import java.util.List;

public interface ChapterService {

    void initSeedChaptersFromJson();

    void addChapterToDB(ChapterServiceModel chapterServiceModel);

    List<ChapterViewModel> getAllChaptersSortedByAtaDesc();

    ChapterServiceModel findChapterByAtaCode(Integer ataCode);

    boolean chapterAtaCodeExists(Integer ataCode);
}
