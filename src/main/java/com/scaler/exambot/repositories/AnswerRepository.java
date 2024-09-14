package com.scaler.exambot.repositories;

import com.scaler.exambot.models.Answer;
import com.scaler.exambot.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    Optional<Answer> findByQuestion(Question question);
}
