package com.scaler.exambot.services;

import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.models.QuestionPaper;

public interface FileGenerationService {
    void generateAnswerSheetToFile(AnswerSheet answerSheet);
    void generateQuestionPaperToFile(QuestionPaper questionPaper);
}
