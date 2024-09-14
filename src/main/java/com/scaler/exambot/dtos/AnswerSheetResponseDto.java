package com.scaler.exambot.dtos;

import com.scaler.exambot.models.AnswerSheet;
import lombok.Data;

@Data
public class AnswerSheetResponseDto {
    private String message;
    private ResponseStatus responseStatus;
    private AnswerSheet answerSheet;
}
