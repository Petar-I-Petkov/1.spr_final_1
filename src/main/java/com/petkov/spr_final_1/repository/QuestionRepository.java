package com.petkov.spr_final_1.repository;

import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {

    Optional<QuestionEntity> findByName(String name);

    Optional<QuestionEntity> findByQuestion(String question);



}
