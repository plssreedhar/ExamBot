package com.scaler.exambot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class AnswerSheet extends BaseModel{
    @OneToMany
    private List<Answer> answers;
    @OneToOne
    private QuestionPaper questionPaper;
}
