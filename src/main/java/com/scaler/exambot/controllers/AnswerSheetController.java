package com.scaler.exambot.controllers;

import com.scaler.exambot.dtos.AnswerSheetRequestDto;
import com.scaler.exambot.dtos.AnswerSheetResponseDto;
import com.scaler.exambot.dtos.ResponseStatus;
import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.services.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerSheetController {

    @Autowired
    AnswerSheetService answerSheetService;

    @PostMapping("/getAnswerSheet")
    public AnswerSheetResponseDto generateAnswerSheet(@RequestBody AnswerSheetRequestDto requestDto){
        AnswerSheetResponseDto answerSheetResponseDto = new AnswerSheetResponseDto();
        try{
            answerSheetResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            AnswerSheet answerSheet = answerSheetService.getAnswerSheet(requestDto.getQuestionPaperId(),requestDto.getGenerateType());
            answerSheetResponseDto.setAnswerSheet(answerSheet);
        }
        catch (Exception e){
            answerSheetResponseDto.setMessage(e.getMessage());
            answerSheetResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return answerSheetResponseDto;
    }
}
