package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;

import java.util.List;

public interface ChapterService {

    void initSeedChaptersFromJson();

    List<ChapterServiceModel> findAllChaptersSortedByATA();

    void addChapterToDB(ChapterServiceModel chapterServiceModel);

    List<String> listAllChaptersAtaAndNameOrderByAtaDesc();

    ChapterEntity findChapterByAtaCode(Integer ataCode);

    boolean chapterAtaCodeExists(Integer ataCode);
}
