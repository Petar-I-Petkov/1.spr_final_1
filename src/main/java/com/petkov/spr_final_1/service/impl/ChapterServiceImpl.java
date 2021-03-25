package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.repository.ChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ModelMapper modelMapper, ChapterRepository chapterRepository) {
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public void addChapterToDB(ChapterServiceModel chapterServiceModel) {


        ChapterEntity chapterEntity = this.modelMapper.map(chapterServiceModel, ChapterEntity.class);

        //todo - /before item save in db/ test point
        System.out.println();

        this.chapterRepository.saveAndFlush(chapterEntity);

    }
}
