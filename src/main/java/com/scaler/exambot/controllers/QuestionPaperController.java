package com.scaler.exambot.controllers;

import com.scaler.exambot.dtos.QuestionPaperRequestDto;
import com.scaler.exambot.dtos.QuestionPaperResponseDto;
import com.scaler.exambot.dtos.ResponseStatus;
import com.scaler.exambot.models.QuestionPaper;
import com.scaler.exambot.services.QuestionPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionPaperController {

    @Autowired
    QuestionPaperService questionPaperService;

    @PostMapping("/getQuestionPaper")
    public QuestionPaperResponseDto generateQuestionPaper(@RequestBody QuestionPaperRequestDto requestDto){
        QuestionPaperResponseDto questionPaperResponseDto = new QuestionPaperResponseDto();
        try{
            questionPaperResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            QuestionPaper questionPaper = questionPaperService.generateQuestionPaper(requestDto.getQuestionsCount(),requestDto.getTopic(),requestDto.getGenerateFile(),requestDto.getQuestionLevel());
            questionPaperResponseDto.setQuestionPaper(questionPaper);
        }
        catch (Exception e){
            e.printStackTrace();
            questionPaperResponseDto.setMessage(e.getMessage());
            questionPaperResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return questionPaperResponseDto;
    }
}
