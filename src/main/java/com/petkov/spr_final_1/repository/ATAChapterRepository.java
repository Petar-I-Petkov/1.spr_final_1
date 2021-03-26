package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ATAChapterRepository extends JpaRepository<ATAChapterEntity, Long> {

}
