package com.petkov.spr_final_1.service.impl;

import com.google.gson.Gson;
import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.repository.ChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ModelMapper modelMapper;
    private final ChapterRepository chapterRepository;
    private final Gson gson;
    private final Resource chaptersInitFile;
    private final ValidationUtil validationUtil;

    public ChapterServiceImpl(ModelMapper modelMapper,
                              ChapterRepository chapterRepository,
                              Gson gson,
                              @Value("classpath:init/chaptersInit.json") Resource chaptersInitFile, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
        this.gson = gson;
        this.chaptersInitFile = chaptersInitFile;
        this.validationUtil = validationUtil;
    }

    @Override
    public void initSeedChapters() {
        if (chapterRepository.count() == 0) {
            try {
                ChapterServiceModel[] chapterServiceModels =
                        gson.fromJson(Files.readString(Path.of(chaptersInitFile.getURI())), ChapterServiceModel[].class);

                Arrays
                        .stream(chapterServiceModels)
                        .forEach(this::seedChaptersIfValidOrPrintError);

            } catch (IOException e) {
                throw new IllegalStateException("IO error from file 'init/chaptersInit.json'!");
            }
        }
    }

    private void seedChaptersIfValidOrPrintError(ChapterServiceModel chapterServiceModel) {

        if (this.validationUtil.isValid(chapterServiceModel)) {

            //chapterServiceModel is valid -> map it to real Chapter, seed to database
            ATAChapterEntity chapter = this.modelMapper.map(chapterServiceModel, ATAChapterEntity.class);
            this.chapterRepository.saveAndFlush(chapter);

        } else {
            //chapterServiceModel is NOT valid -> print messages
            System.out.println(String.format("Chapter init seed errors from file 'init/chaptersInit.json' %n: "));

            this.validationUtil.getViolations(chapterServiceModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.printf("For chapter '%s' - '%s' %n",chapterServiceModel.getAtaChapter(), chapterServiceModel.getName());
        }
    }

    @Override
    public void addChapterToDB(ChapterServiceModel chapterServiceModel) {

        ATAChapterEntity ATAChapterEntity = this.modelMapper.map(chapterServiceModel, ATAChapterEntity.class);
        this.chapterRepository.saveAndFlush(ATAChapterEntity);

    }
}
