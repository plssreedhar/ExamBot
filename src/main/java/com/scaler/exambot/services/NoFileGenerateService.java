package com.scaler.exambot.services;

import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.models.QuestionPaper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NoFileGenerateService implements FileGenerationService {

    @Override
    public void generateAnswerSheetToFile(AnswerSheet answerSheet) {
        return;
    }

    @Override
    public void generateQuestionPaperToFile(QuestionPaper questionPaper) {
        return;
    }
}
