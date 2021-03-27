package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.ATASubChapterEntity;
import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;
import com.petkov.spr_final_1.repository.ATASubChapterRepository;
import com.petkov.spr_final_1.service.ATAChapterService;
import com.petkov.spr_final_1.service.ATASubchapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ATASubchapterServiceImpl implements ATASubchapterService {


    private final ModelMapper modelMapper;
    private final ATASubChapterRepository ataSubChapterRepository;
    private final ATAChapterService ataChapterService;

    public ATASubchapterServiceImpl(ModelMapper modelMapper, ATASubChapterRepository ataSubChapterRepository, ATAChapterService ataChapterService) {
        this.modelMapper = modelMapper;
        this.ataSubChapterRepository = ataSubChapterRepository;
        this.ataChapterService = ataChapterService;
    }


    @Override
    @Transactional
    public void seedATASubchapterToDb(ATASubChapterServiceModel ataSubChapterServiceModel) {

        //
//        TypeMap<ATASubChapterServiceModel, ATASubChapterEntity> typeMap =
//                modelMapper.createTypeMap(ATASubChapterServiceModel.class, ATASubChapterEntity.class);
//
//        typeMap.addMappings(mapper -> mapper.skip(ATASubChapterEntity::setAtaSubCode));
//
//        ATASubChapterEntity ataSubChapterEntity =
//                modelMapper.map(ataSubChapterServiceModel, ATASubChapterEntity.class);

        ATASubChapterEntity ataSubChapterEntity = new ATASubChapterEntity();

        ataSubChapterEntity.setAtaSubCode(ataSubChapterServiceModel.getAtaSubCode());
        ataSubChapterEntity.setSubchapterName(ataSubChapterServiceModel.getSubchapterName());

        int ataChapterCode = Integer.parseInt(ataSubChapterServiceModel.
                getAtaChapterRefInput().split(" ")[0]);

        ATAChapterEntity ataChapterEntity =
                this.ataChapterService.findAtaChapterByCode(ataChapterCode);

        ataSubChapterEntity.setAtaChapterRef(ataChapterEntity);

        this.ataSubChapterRepository.saveAndFlush(ataSubChapterEntity);

    }

    @Override
    @Transactional
    public boolean subChapterCodeExists(Integer ataSubCode, String ataChapterRefInput) {

        ATASubChapterEntity ataSubChapterEntity =
                this.ataSubChapterRepository.findByAtaSubCode(ataSubCode).orElse(null);

        return ataSubChapterEntity != null &&
                ataSubChapterEntity.getAtaChapterRef().getAtaChapter() == Integer.parseInt(ataChapterRefInput.split(" ")[0]);
    }
}
