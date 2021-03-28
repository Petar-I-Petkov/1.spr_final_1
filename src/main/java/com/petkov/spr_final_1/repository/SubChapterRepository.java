package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubChapterRepository extends JpaRepository<SubChapterEntity, String> {

    Optional<SubChapterEntity> findByAtaSubCode(Integer ataSubCode);
}
