package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.entity.document.DocumentSubchapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentSubchapterRepository extends JpaRepository<DocumentSubchapterEntity, String> {

    Optional<DocumentSubchapterEntity> findByDocumentAndName(DocumentEntity document,
                                                                          String subchapterName);

}
