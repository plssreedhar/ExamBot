package com.scaler.exambot.services;

import com.scaler.exambot.models.FileGenerateType;
import com.scaler.exambot.models.Question;
import com.scaler.exambot.models.QuestionLevel;
import com.scaler.exambot.models.QuestionPaper;
import com.scaler.exambot.repositories.QuestionPaperRepository;
import com.scaler.exambot.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionPaperService {

    @Autowired
    ChatService chatService;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionPaperRepository questionPaperRepository;

    @Autowired
    FileFactory fileFactory;

//    private String question = "1. Explain the Singleton Pattern. What are its key characteristics, and in which scenarios is it most appropriately used?\n" +
//            "2. Compare and contrast the Adapter Pattern and the Facade Pattern. Provide an example scenario where each would be used.\n" +
//            "3. Describe the Observer Pattern. How does it support decoupling in an object-oriented system? Give an example of its implementation.\n" +
//            "4. What is the difference between the Factory Method Pattern and the Abstract Factory Pattern? Provide a use case for each.\n" +
//            "5. How does the Strategy Pattern enable flexible design? Illustrate with an example where it can replace conditional logic.\n" +
//            "6. Discuss the Command Pattern. How does it encapsulate a request as an object, and what are the benefits of this encapsulation?\n" +
//            "7. Explain the Decorator Pattern. How can it be used to extend the functionalities of objects dynamically? Provide a code example.\n" +
//            "8. What are the main differences between the Proxy Pattern and the Decorator Pattern? In which scenarios would you choose one over the other?\n" +
//            "9. Describe the Chain of Responsibility Pattern. How does it promote loose coupling, and what are some practical applications of this pattern?\n" +
//            "10. Explain the concept of the State Pattern. How does it allow an object to alter its behavior when its internal state changes? Provide an example of its implementation.";

    public QuestionPaper generateQuestionPaper(int questionsCount, String topic, FileGenerateType generateFile, QuestionLevel questionLevel) {
        QuestionPaper questionPaper = new QuestionPaper();
        questionPaper.setNumberOfQuestions(questionsCount);
        questionPaper.setQuestionLevel(questionLevel);
        questionPaper.setTopic(topic);
        StringBuilder message = new StringBuilder("Generate ");
        message.append(questionsCount).append(" questions only on topic ")
                .append(topic).append(" at ")
                .append(questionLevel)
                .append(" level for exam preparation purpose.");
        String response;
        try {
            response = chatService.chat(message.toString());
        }
        catch (Exception e){
            throw new RuntimeException("Chat gpt is failed in generating questions!",e);
        }
        List<Question> questions = extractQuestionsFromGPTResponse(response);
        questionPaper.setQuestions(questions);
        questionPaperRepository.save(questionPaper);
        FileGenerationService fileGenerationFactory = fileFactory.getFileGenerationFactory(generateFile);
        fileGenerationFactory.generateQuestionPaperToFile(questionPaper);
        return questionPaper;
    }

    private List<Question> extractQuestionsFromGPTResponse(String response) {
        String splitByQuestionNumber = "\\d+\\. ";
//        String startsWithInteger = "^\\d+.*";
        String[] questions = response.split(splitByQuestionNumber);
        List<Question> questionList = new ArrayList<>();
        for(String stringQuestion : questions){
            if(!stringQuestion.isBlank()) {
                Question question = new Question(stringQuestion);
                questionRepository.save(question);
                questionList.add(question);
            }
        }
        return questionList;
    }
}
