package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.document.ATAChapterEntity;
import com.petkov.spr_final_1.model.entity.document.ATASubChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ATASubChapterRepository extends JpaRepository<ATASubChapterEntity, Long> {

    Optional<ATASubChapterEntity> findByAtaChapterRefAndAtaSubCode(
            ATAChapterEntity ataChapterRef, Integer ataSubCode);

}
