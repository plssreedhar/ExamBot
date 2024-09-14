package com.scaler.exambot.services;

import com.scaler.exambot.models.Answer;
import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.models.Question;
import com.scaler.exambot.models.QuestionPaper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class TextFileGenerateService implements FileGenerationService {
    @Override
    public void generateAnswerSheetToFile(AnswerSheet answerSheet) {
        String fileName = "D:\\lld-codes\\ExamBot\\AnswerSheets\\AnswerSheet-"+answerSheet.getQuestionPaper().getId()+".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Answer sheet : " + answerSheet.getId());
            writer.newLine();
            writer.newLine();
            List<Answer> answers = answerSheet.getAnswers();
            for (int i = 1; i <= answers.size(); i++) {
                Answer answer = answers.get(i - 1);
                writer.write("Question-"+i + ". " + answer.getQuestion().getQuestion());
                writer.newLine();
                writer.write("Answer: ");
                writer.newLine();
                writer.write(answer.getAnswer());
                writer.newLine();
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateQuestionPaperToFile(QuestionPaper questionPaper) {
        String fileName = "D:\\lld-codes\\ExamBot\\QuestionPapers\\QuestionPaper-"+questionPaper.getId()+".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write("Question Paper : " +new Date().toString());
            writer.newLine();
            List<Question> questions = questionPaper.getQuestions();
            for (int i = 1; i <= questions.size(); i++) {
                writer.write(i + ". " + questions.get(i - 1).getQuestion());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
