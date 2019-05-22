package com.example.cheggexercise.scanner;

import com.example.cheggexercise.FileReader;
import com.example.cheggexercise.csv.CsvFileReader;
import com.example.cheggexercise.domain.SourceType;
import com.example.cheggexercise.json.JsonFileReader;
import com.example.cheggexercise.ocr.OCRFileReader;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Component
public class QuestionDataSourceScannerImpl implements QuestionDataSourceScanner, FileReader {
    private static final Logger logger = LoggerFactory.getLogger(QuestionDataSourceScannerImpl.class);

    @Value("${links.of.question.files:https://bitbucket.org/cheggil/fullstack-home-assignment/raw/a5d09430c5781b8c15f6e887b0cfb921ca5a757c/manifest.dat}")
    String linksURL;

    @Autowired
    ImageValidator imageValidator;
    @Autowired
    CsvFileReader csvFileReader;
    @Autowired
    JsonFileReader jsonFileReader;
    @Autowired
    OCRFileReader ocrFileReader;

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void startReadingQuestionDataSources() {
        logger.debug("web application started");

        try {
            Path path = copyRemoteFile(linksURL);
            if(path==null){
                logger.error("question data sources link {} not found ", linksURL);
            }
            //assume file is not very big for in memory other wise use Scanner readLine()
            List<String> list = Files.readAllLines(path);
            if(list!=null){

                for (String questionDataSource : list) {
                    if (Strings.isBlank(questionDataSource)) {
                        continue;
                    }
                    if(questionDataSource.endsWith(SourceType.csv.toString())){
                        csvFileReader.readCsvFile(questionDataSource);
                    }else if(questionDataSource.endsWith(SourceType.json.toString())){
                        jsonFileReader.readJsonFile(questionDataSource);
                    } else if(imageValidator.isImage(questionDataSource)) {
                        ocrFileReader.readQuestionFromOCR(questionDataSource);
                    } else { //image
                        logger.error("questionDataSource file type {} is not supported ",linksURL);
                    }
                }
            }

            path.toFile().delete();
        } catch (IOException exp) {
            logger.error("Fail to read question data sources link "+linksURL, exp);
        }

    }


}
