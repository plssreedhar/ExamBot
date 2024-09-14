package com.scaler.exambot.services;

import com.scaler.exambot.models.*;
import com.scaler.exambot.repositories.AnswerRepository;
import com.scaler.exambot.repositories.AnswerSheetRepository;
import com.scaler.exambot.repositories.QuestionPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerSheetService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerSheetRepository answerSheetRepository;

    @Autowired
    QuestionPaperRepository questionPaperRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    FileFactory fileFactory;



    public AnswerSheet getAnswerSheet(int questionPaperId, FileGenerateType generateFile) {
        Optional<QuestionPaper> questionPaperOptional = questionPaperRepository.findById(questionPaperId);
        if(questionPaperOptional.isEmpty()){
            throw new RuntimeException("Question Paper with given id is not available!");
        }
        QuestionPaper questionPaper = questionPaperOptional.get();
        Optional<AnswerSheet> answerSheetOptional = answerSheetRepository.findByQuestionPaper(questionPaper);
        if(answerSheetOptional.isPresent()){
            AnswerSheet answerSheet = answerSheetOptional.get();
            FileGenerationService fileGenerationFactory = fileFactory.getFileGenerationFactory(generateFile);
            fileGenerationFactory.generateAnswerSheetToFile(answerSheet);
            return answerSheet;
        }
        AnswerSheet answerSheet = new AnswerSheet();
        List<Answer> answers = new ArrayList<>();
        for(Question question: questionPaper.getQuestions()){
            Answer answer;
            Optional<Answer> answerOptional = answerRepository.findByQuestion(question);
            answer = answerOptional.orElseGet(Answer::new);
            if(answerOptional.isEmpty()) {
                String ans ;
                try {
                    ans = chatService.chat(question.getQuestion());
                }
                catch (Exception e){
                    throw new RuntimeException("Chat gpt failed in giving answer!",e);
                }

                answer.setAnswer(ans);
                answer.setQuestion(question);
                answerRepository.save(answer);
            }
            answers.add(answer);
        }
        answerSheet.setAnswers(answers);
        FileGenerationService fileGenerationFactory = fileFactory.getFileGenerationFactory(generateFile);
        fileGenerationFactory.generateAnswerSheetToFile(answerSheet);
        answerSheet.setQuestionPaper(questionPaper);
        return answerSheetRepository.save(answerSheet);
    }
}
