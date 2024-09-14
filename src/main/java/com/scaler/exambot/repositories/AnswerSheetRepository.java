package com.scaler.exambot.repositories;

import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.models.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerSheetRepository extends JpaRepository<AnswerSheet,Integer> {
    Optional<AnswerSheet> findByQuestionPaper(QuestionPaper questionPaper);
}
