package com.scaler.exambot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity(name = "Questions")
public class Question extends BaseModel{
    private String question;

    public Question(String question) {
        this.question = question;
    }

    public Question() {

    }
}
