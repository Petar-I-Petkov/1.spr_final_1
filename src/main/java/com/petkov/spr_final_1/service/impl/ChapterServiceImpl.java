package com.petkov.spr_final_1.service.impl;

import com.google.gson.Gson;
import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.repository.ChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.utils.ValidationUtil;
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
    private final ChapterRepository chapterRepository;
    private final Gson gson;
    private final Resource chaptersInitFile;
    private final ValidationUtil validationUtil;

    public ChapterServiceImpl(ModelMapper modelMapper,
                              ChapterRepository chapterRepository,
                              Gson gson,
                              @Value("classpath:init/chapters-init.json") Resource chaptersInitFile,
                              ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.chapterRepository = chapterRepository;
        this.gson = gson;
        this.chaptersInitFile = chaptersInitFile;
        this.validationUtil = validationUtil;
    }

    @Override
    public void initSeedChaptersFromJson() {
        if (chapterRepository.count() == 0) {
            try {
                ChapterServiceModel[] chapterServiceModels =
                        gson.fromJson(Files.readString(Path.of(chaptersInitFile.getURI())), ChapterServiceModel[].class);

                Arrays
                        .stream(chapterServiceModels)
                        .forEach(this::seedChaptersIfValidOrPrintError);

            } catch (IOException e) {
                throw new IllegalStateException("IO error from file 'init/chapters-init.json'!");
            }
        }
    }

    private void seedChaptersIfValidOrPrintError(ChapterServiceModel chapterServiceModel) {

        if (this.validationUtil.isValid(chapterServiceModel)) {

            //chapterServiceModel is valid -> map it to real Chapter, seed to database
            ChapterEntity chapter = this.modelMapper.map(chapterServiceModel, ChapterEntity.class);
            this.chapterRepository.saveAndFlush(chapter);

        } else {
            //chapterServiceModel is NOT valid -> print messages
            System.out.println(String.format("Chapter init seed errors from file 'init/chapters-init.json' %n: "));

            this.validationUtil.getViolations(chapterServiceModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.printf("For chapter '%s' - '%s' %n", chapterServiceModel.getAtaCode(), chapterServiceModel.getName());
        }
    }

    @Override
    public void addChapterToDB(ChapterServiceModel chapterServiceModel) {

        ChapterEntity chapterEntity = this.modelMapper.map(chapterServiceModel, ChapterEntity.class);
        this.chapterRepository.saveAndFlush(chapterEntity);

    }

    @Override
    public List<String> listAllChaptersAtaAndNameOrderByAtaDesc() {

        List<ChapterEntity> allChapters =
                this.chapterRepository.findAll((Sort.by(Sort.Direction.ASC, "ataCode")));

        List<String> chaptersAtaAndNameExportList =
                allChapters
                        .stream()
                        .map(chapter -> String.format("%02d - %s ", chapter.getAtaCode(), chapter.getName()))
                        .collect(Collectors.toList());

        return chaptersAtaAndNameExportList;
    }

    @Override
    public ChapterEntity findChapterByAtaCode(Integer ataCode) {

       return this.chapterRepository.findByAtaCode(ataCode)
                .orElseThrow(() -> new IllegalArgumentException("Chapter could not be found in DB"));

    }

    @Override
    public boolean chapterAtaCodeExists(Integer ataCode) {
        return this.chapterRepository.findByAtaCode(ataCode).isPresent();
    }
}
