package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;

public interface ATASubchapterService {

    void seedATASubchapterToDb(ATASubChapterServiceModel ataSubChapterServiceModel);

    boolean subChapterAtaCodeExists(Integer ataSubCode);


}
