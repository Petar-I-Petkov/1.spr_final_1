package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.documentEntities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {

    Optional<DocumentEntity> findByDocumentName(String documentName);

}
