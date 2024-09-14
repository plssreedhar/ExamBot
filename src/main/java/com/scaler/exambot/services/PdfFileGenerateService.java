package com.scaler.exambot.services;

import com.scaler.exambot.models.Answer;
import com.scaler.exambot.models.AnswerSheet;
import com.scaler.exambot.models.Question;
import com.scaler.exambot.models.QuestionPaper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service
public class PdfFileGenerateService implements FileGenerationService {


    private static final float MARGIN = 50;
    private static final float FONT_SIZE = 12;
    PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
    private static final float PAGE_HEIGHT = PDRectangle.LETTER.getHeight();
    private static final float PAGE_WIDTH  = PDRectangle.LETTER.getWidth() - MARGIN;


    @Override
    public void generateAnswerSheetToFile(AnswerSheet answerSheet) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        float yPosition = PAGE_HEIGHT - MARGIN;

        String fileName = "D:\\lld-codes\\ExamBot\\AnswerSheets\\AnswerSheet-"+answerSheet.getQuestionPaper().getId()+".pdf";

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(pdType1Font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(MARGIN, yPosition);
            contentStream.showText("Answer sheet : " + answerSheet.getId());
            contentStream.newLineAtOffset(0,-20);
            yPosition-=20;
            contentStream.newLineAtOffset(0,-20);
            yPosition-=20;
            List<Answer> answers = answerSheet.getAnswers();
            for (int i = 1; i <= answers.size(); i++) {
                Answer answer = answers.get(i - 1);
                String question = answer.getQuestion().getQuestion();
                String ans = answer.getAnswer();
                String[] lines = question.split("\n");
                for(String line : lines) {
                    PDPageContentStream newContentStream = checkAndAddPage(document,contentStream,yPosition);
                    if(newContentStream!=contentStream){
                        contentStream = newContentStream;
                        yPosition = PAGE_HEIGHT-MARGIN;
                    }
                    if(line.equals(lines[0])) {
                        yPosition = widthFitter(contentStream,"Question "+i + ". " + line,yPosition);
                    }
                    else{
                        yPosition = widthFitter(contentStream,line,yPosition);
                    }
                    contentStream.newLineAtOffset(0, -20);
                    yPosition-=20;
                }
                contentStream.showText("Answer: ");
                contentStream.newLineAtOffset(0,-20);
                yPosition-=20;
                String[] answerLines = ans.split("\n");
                for(String line : answerLines) {
                    PDPageContentStream newContentStream = checkAndAddPage(document,contentStream,yPosition);
                    if(newContentStream!=contentStream){
                        contentStream = newContentStream;
                        yPosition = PAGE_HEIGHT-MARGIN;
                    }
                    if(line.equals(lines[0])) {
                        yPosition = widthFitter(contentStream,i + ". " + line,yPosition);
                    }
                    else{
                        yPosition = widthFitter(contentStream,line,yPosition);
                    }
                }
                contentStream.newLineAtOffset(0,-20);
                yPosition-=20;
                contentStream.newLineAtOffset(0,-20);
                yPosition-=20;
            }
            contentStream.endText();
            contentStream.close();

            document.save(fileName);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private PDPageContentStream checkAndAddPage(PDDocument document,PDPageContentStream pdPageContentStream,float yPosition) throws IOException {

        if(yPosition<= MARGIN) {
            pdPageContentStream.endText();
            pdPageContentStream.close();
            PDPage page = new PDPage();
            document.addPage(page);
            pdPageContentStream = new PDPageContentStream(document, page);
            pdPageContentStream.setFont(pdType1Font, 12);
            pdPageContentStream.beginText();
            pdPageContentStream.newLineAtOffset(MARGIN, PAGE_HEIGHT-MARGIN);
        }
        return pdPageContentStream;
    }

    @Override
    public void generateQuestionPaperToFile(QuestionPaper questionPaper) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        float yPosition = PAGE_HEIGHT - MARGIN;

        String fileName = "D:\\lld-codes\\ExamBot\\QuestionPapers\\QuestionPaper-"+questionPaper.getId()+".pdf";

        try {
            PDType1Font pdType1Font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(pdType1Font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(MARGIN, yPosition);
            contentStream.showText("Question Paper : "+new Date().toString());
            contentStream.newLineAtOffset(0, -20);
            yPosition-=20;
            List<Question> questions = questionPaper.getQuestions();
            for (int i = 1; i <= questions.size(); i++) {
                String question = questions.get(i - 1).getQuestion();
                String[] lines = question.split("\n");
                for(String line : lines) {
                    PDPageContentStream newContentStream = checkAndAddPage(document,contentStream,yPosition);
                    if(newContentStream!=contentStream){
                        contentStream = newContentStream;
                        yPosition = PAGE_HEIGHT-MARGIN;
                    }
                    if(line.equals(lines[0])) {
                        yPosition = widthFitter(contentStream,"Question "+i + ". " + line,yPosition);
                    }
                    else{
                        yPosition = widthFitter(contentStream,line,yPosition);
                    }
                }
            }
            contentStream.endText();
            contentStream.close();

            document.save(fileName);
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private float widthFitter(PDPageContentStream contentStream,String line,float yPosition) throws IOException {
        StringBuilder sb = new StringBuilder();
        String[] words = line.split(" ");
        for(String word : words){
            sb.append(" ");
            sb.append(word);
            if(sb.length()>90){
                contentStream.showText(sb.toString());
                contentStream.newLineAtOffset(0,-20);
                yPosition-=20;
                sb = new StringBuilder();
            }
        }
        contentStream.showText(sb.toString());
        contentStream.newLineAtOffset(0,-20);
        return yPosition-20;
    }
}

