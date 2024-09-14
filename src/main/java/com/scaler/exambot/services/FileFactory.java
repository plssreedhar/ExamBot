package com.scaler.exambot.services;

import com.scaler.exambot.models.FileGenerateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileFactory {

    @Autowired
     FileGenerationService pdfFileGenerateService;
    @Autowired
     FileGenerationService textFileGenerateService;
    @Autowired
     FileGenerationService noFileGenerateService;

    public FileGenerationService getFileGenerationFactory(FileGenerateType fileGenerateType) {

        if (fileGenerateType==FileGenerateType.PDF) {
            return pdfFileGenerateService;
        }
        else if (fileGenerateType==FileGenerateType.TXT) {
            return textFileGenerateService;
        }
        else {
            return noFileGenerateService;
        }
    }
}
