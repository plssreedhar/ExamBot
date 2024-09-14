package com.scaler.exambot.dtos;

import com.scaler.exambot.models.FileGenerateType;
import lombok.Data;

@Data
public class AnswerSheetRequestDto {
    private int questionPaperId;
    private FileGenerateType generateType;

}
