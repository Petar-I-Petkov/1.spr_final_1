package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import com.petkov.spr_final_1.model.service.SubChapterServiceModel;
import com.petkov.spr_final_1.repository.ATASubChapterRepository;
import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.ATASubchapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ATASubchapterServiceImpl implements ATASubchapterService {


    private final ModelMapper modelMapper;
    private final ATASubChapterRepository ataSubChapterRepository;
    private final ChapterService ataChapterService;

    public ATASubchapterServiceImpl(ModelMapper modelMapper, ATASubChapterRepository ataSubChapterRepository, ChapterService ataChapterService) {
        this.modelMapper = modelMapper;
        this.ataSubChapterRepository = ataSubChapterRepository;
        this.ataChapterService = ataChapterService;
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
                getAtaChapterRefInput().split(" ")[0]);

        ChapterEntity chapterEntity =
                this.ataChapterService.findChapterByAtaCode(ataChapterCode);

        subChapterEntity.setAtaChapterRef(chapterEntity);

        this.ataSubChapterRepository.saveAndFlush(subChapterEntity);

    }

    @Override
    @Transactional
    public boolean subChapterCodeExists(Integer ataSubCode, String ataChapterRefInput) {

        SubChapterEntity subChapterEntity =
                this.ataSubChapterRepository.findByAtaSubCode(ataSubCode).orElse(null);

        return subChapterEntity != null &&
                subChapterEntity.getAtaChapterRef().getAtaCode() == Integer.parseInt(ataChapterRefInput.split(" ")[0]);
    }
}
