package com.scaler.exambot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "answers")
public class Answer extends BaseModel{
    @OneToOne
    private Question question;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String answer;
}
