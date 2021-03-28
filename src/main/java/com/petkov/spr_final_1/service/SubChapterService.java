package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.SubChapterServiceModel;

public interface SubChapterService {

    void seedATASubchapterToDb(SubChapterServiceModel subChapterServiceModel);

    boolean subChapterCodeExists(Integer ataSubCode, String ataChapterRefInput);

    void initSeedSubchaptersFromJson();
}
