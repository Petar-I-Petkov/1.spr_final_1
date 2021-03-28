package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ATASubChapterRepository extends JpaRepository<SubChapterEntity, Long> {

    Optional<SubChapterEntity> findByAtaSubCode(Integer ataSubCode);
}
