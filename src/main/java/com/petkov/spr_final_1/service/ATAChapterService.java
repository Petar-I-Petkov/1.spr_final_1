package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import com.petkov.spr_final_1.model.service.ATAChapterServiceModel;

import java.util.List;

public interface ATAChapterService {

    void initSeedChapters();

    void addChapterToDB(ATAChapterServiceModel ataChapterServiceModel);

    List<String> listAllChaptersAtaAndNameOrderByAtaDesc();

    ATAChapterEntity findAtaChapterByCode(Integer ataChapterCode);

    boolean chapterAtaCodeExists(Integer ataChapter);
}
