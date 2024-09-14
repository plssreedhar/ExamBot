package com.scaler.exambot.repositories;

import com.scaler.exambot.models.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper,Integer> {
}
