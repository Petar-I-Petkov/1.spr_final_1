package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.document.ATAChapterServiceModel;
import com.petkov.spr_final_1.model.view.ATAChapterViewModel;

import java.util.List;

public interface ATAChapterService {

    void initSeedChaptersFromJson();

    void addChapterToDB(ATAChapterServiceModel chapterServiceModel);

    List<ATAChapterViewModel> getAllChaptersSortedByAtaDesc();

    ATAChapterServiceModel findChapterByAtaCode(Integer ataCode);

    boolean chapterAtaCodeExists(Integer ataCode);
}
