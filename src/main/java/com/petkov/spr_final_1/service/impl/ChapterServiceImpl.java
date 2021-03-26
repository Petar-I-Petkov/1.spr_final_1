package com.petkov.spr_final_1.service.impl;

import com.google.gson.Gson;
import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import com.petkov.spr_final_1.model.service.ATAChapterServiceModel;
import com.petkov.spr_final_1.repository.ATAChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ModelMapper modelMapper;
    private final ATAChapterRepository ATAChapterRepository;
    private final Gson gson;
    private final Resource chaptersInitFile;
    private final ValidationUtil validationUtil;

    public ChapterServiceImpl(ModelMapper modelMapper,
                              ATAChapterRepository ATAChapterRepository,
                              Gson gson,
                              @Value("classpath:init/chaptersInit.json") Resource chaptersInitFile, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.ATAChapterRepository = ATAChapterRepository;
        this.gson = gson;
        this.chaptersInitFile = chaptersInitFile;
        this.validationUtil = validationUtil;
    }

    @Override
    public void initSeedChapters() {
        if (ATAChapterRepository.count() == 0) {
            try {
                ATAChapterServiceModel[] ATAChapterServiceModels =
                        gson.fromJson(Files.readString(Path.of(chaptersInitFile.getURI())), ATAChapterServiceModel[].class);

                Arrays
                        .stream(ATAChapterServiceModels)
                        .forEach(this::seedChaptersIfValidOrPrintError);

            } catch (IOException e) {
                throw new IllegalStateException("IO error from file 'init/chaptersInit.json'!");
            }
        }
    }

    private void seedChaptersIfValidOrPrintError(ATAChapterServiceModel ATAChapterServiceModel) {

        if (this.validationUtil.isValid(ATAChapterServiceModel)) {

            //chapterServiceModel is valid -> map it to real Chapter, seed to database
            ATAChapterEntity chapter = this.modelMapper.map(ATAChapterServiceModel, ATAChapterEntity.class);
            this.ATAChapterRepository.saveAndFlush(chapter);

        } else {
            //chapterServiceModel is NOT valid -> print messages
            System.out.println(String.format("Chapter init seed errors from file 'init/chaptersInit.json' %n: "));

            this.validationUtil.getViolations(ATAChapterServiceModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.printf("For chapter '%s' - '%s' %n", ATAChapterServiceModel.getAtaChapter(), ATAChapterServiceModel.getName());
        }
    }

    @Override
    public void addChapterToDB(ATAChapterServiceModel ataChapterServiceModel) {

        ATAChapterEntity ATAChapterEntity = this.modelMapper.map(ataChapterServiceModel, ATAChapterEntity.class);
        this.ATAChapterRepository.saveAndFlush(ATAChapterEntity);

    }

    @Override
    public List<String> listAllChaptersAtaAndNameOrderByAtaDesc() {

        List<ATAChapterEntity> allChapters =
                this.ATAChapterRepository.findAll((Sort.by(Sort.Direction.ASC, "ataChapter")));

        List<String> chaptersAtaAndNameExportList =
                allChapters
                        .stream()
                        .map(chapter -> String.format("%02d - %s ", chapter.getAtaChapter(), chapter.getName()))
                        .collect(Collectors.toList());

        return chaptersAtaAndNameExportList;
    }
}
