package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ATASubChapterEntity;
import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;
import com.petkov.spr_final_1.repository.ATASubChapterRepository;
import com.petkov.spr_final_1.service.ATASubchapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ATASubchapterServiceImpl implements ATASubchapterService {


    private final ModelMapper modelMapper;
    private final ATASubChapterRepository ataSubChapterRepository;

    public ATASubchapterServiceImpl(ModelMapper modelMapper, ATASubChapterRepository ataSubChapterRepository) {
        this.modelMapper = modelMapper;
        this.ataSubChapterRepository = ataSubChapterRepository;
    }


    @Override
    public void seedATASubchapterToDb(ATASubChapterServiceModel ataSubChapterServiceModel) {

        ATASubChapterEntity ataSubChapterEntity =
                modelMapper.map(ataSubChapterServiceModel, ATASubChapterEntity.class);

        //todo seedATASubchapterToDb debug point
        System.out.println();


    }
}
