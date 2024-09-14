package com.scaler.exambot.dtos;

import com.scaler.exambot.models.QuestionPaper;
import lombok.Data;

@Data
public class QuestionPaperResponseDto {
    private QuestionPaper questionPaper;
    private ResponseStatus responseStatus;
    private String message;
}
