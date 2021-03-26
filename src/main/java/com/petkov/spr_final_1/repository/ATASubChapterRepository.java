package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.ATASubChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATASubChapterRepository extends JpaRepository<ATASubChapterEntity, Long> {
}
