package com.petkov.spr_final_1.service.impl;

import com.google.gson.Gson;
import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import com.petkov.spr_final_1.model.service.SubChapterServiceModel;
import com.petkov.spr_final_1.repository.SubChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.SubChapterService;
import com.petkov.spr_final_1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class SubChapterServiceImpl implements SubChapterService {


    private final ModelMapper modelMapper;
    private final SubChapterRepository subChapterRepository;
    private final ChapterService chapterService;
    private final Resource subChaptersInitFile;
    private final ValidationUtil validationUtil;
    private final Gson gson;


    public SubChapterServiceImpl(ModelMapper modelMapper,
                                 SubChapterRepository subChapterRepository,
                                 ChapterService chapterService,
                                 @Value("classpath:init/sub-chapters-init.json") Resource subChaptersInitFile,
                                 ValidationUtil validationUtil, Gson gson) {
        this.modelMapper = modelMapper;
        this.subChapterRepository = subChapterRepository;
        this.chapterService = chapterService;
        this.subChaptersInitFile = subChaptersInitFile;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    @Transactional
    public void seedATASubchapterToDb(SubChapterServiceModel subChapterServiceModel) {

        //
//        TypeMap<ATASubChapterServiceModel, ATASubChapterEntity> typeMap =
//                modelMapper.createTypeMap(ATASubChapterServiceModel.class, ATASubChapterEntity.class);
//
//        typeMap.addMappings(mapper -> mapper.skip(ATASubChapterEntity::setAtaSubCode));
//
//        ATASubChapterEntity ataSubChapterEntity =
//                modelMapper.map(ataSubChapterServiceModel, ATASubChapterEntity.class);

        SubChapterEntity subChapterEntity = new SubChapterEntity();

        subChapterEntity.setAtaSubCode(subChapterServiceModel.getAtaSubCode());
        subChapterEntity.setSubchapterName(subChapterServiceModel.getSubchapterName());

        int ataChapterCode = Integer.parseInt(subChapterServiceModel.
                getAtaChapterRefInput());

        ChapterEntity chapterEntity =
                this.chapterService.findChapterByAtaCode(ataChapterCode);

        subChapterEntity.setAtaChapterRef(chapterEntity);

        this.subChapterRepository.saveAndFlush(subChapterEntity);

    }

    @Override
    @Transactional
    public boolean subChapterCodeExists(Integer ataSubCode, String ataChapterRefInput) {

        SubChapterEntity subChapterEntity =
                this.subChapterRepository.findByAtaSubCode(ataSubCode).orElse(null);

        return subChapterEntity != null &&
                subChapterEntity.getAtaChapterRef().getAtaCode() == Integer.parseInt(ataChapterRefInput.split(" ")[0]);
    }

    @Override
    public void initSeedSubchaptersFromJson() {
        if (subChapterRepository.count() == 0) {

            try {
                SubChapterServiceModel[] subChapterServiceModels =
                        gson.fromJson(Files.readString(Path.of(subChaptersInitFile.getURI())), SubChapterServiceModel[].class);

                Arrays
                        .stream(subChapterServiceModels)
                        .forEach(this::seedSubChaptersIfValidOrPrintError);

            } catch (IOException e) {
                throw new IllegalStateException("IO error from file 'init/sub-chapters-init.json'!");
            }
        }
    }

    private void seedSubChaptersIfValidOrPrintError(SubChapterServiceModel subChapterServiceModel) {

        if (this.validationUtil.isValid(subChapterServiceModel)) {

            seedATASubchapterToDb(subChapterServiceModel);

        } else {
            //chapterServiceModel is NOT valid -> print messages
            System.out.println(String.format("Chapter init seed errors from file 'init/sub-chapters-init.json' %n: "));

            this.validationUtil.getViolations(subChapterServiceModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            System.out.printf("For chapter '%s'-'%s' - '%s' %n",
                    subChapterServiceModel.getAtaChapterRefInput(),
                    subChapterServiceModel.getAtaSubCode(),
                    subChapterServiceModel.getSubchapterName());
        }
    }

}