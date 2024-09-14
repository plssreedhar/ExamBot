package com.scaler.exambot.dtos;

import com.scaler.exambot.models.FileGenerateType;
import com.scaler.exambot.models.QuestionLevel;
import lombok.Data;

@Data
public class QuestionPaperRequestDto {
    private String topic;
    private int questionsCount;
    private FileGenerateType generateFile;
    private QuestionLevel questionLevel;
}
