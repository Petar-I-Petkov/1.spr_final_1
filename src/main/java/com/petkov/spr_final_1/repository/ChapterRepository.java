package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {

    Optional<ChapterEntity> findByAtaCode(Integer ataCode);

}
