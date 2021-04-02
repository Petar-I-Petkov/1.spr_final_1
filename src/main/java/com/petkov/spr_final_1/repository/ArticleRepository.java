package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.document.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    Optional<ArticleEntity> findByTitle(String title);
}
