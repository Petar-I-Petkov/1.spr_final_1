package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.document.ATAChapterServiceModel;
import com.petkov.spr_final_1.model.view.ATAChapterViewModel;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ATAChapterService {

    void initSeedChaptersFromJson();

    void addChapterToDB(ATAChapterServiceModel chapterServiceModel);

    CompletableFuture<List<ATAChapterViewModel>> getAllChaptersSortedByAtaDesc();

    ATAChapterServiceModel findChapterByAtaCode(Integer ataCode) throws IllegalArgumentException;

    boolean chapterAtaCodeExists(Integer ataCode);
}
