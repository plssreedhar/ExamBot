package com.scaler.exambot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class QuestionPaper extends BaseModel {
    private String topic;
    private int numberOfQuestions;
    @OneToMany
    private List<Question> questions;
    @Enumerated(EnumType.STRING)
    private QuestionLevel questionLevel;
}
