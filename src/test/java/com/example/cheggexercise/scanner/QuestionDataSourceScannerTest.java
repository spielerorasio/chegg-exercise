package com.example.cheggexercise.scanner;

import com.example.cheggexercise.csv.CsvFileReader;
import com.example.cheggexercise.csv.CsvFileReaderImpl;
import com.example.cheggexercise.json.JsonFileReader;
import com.example.cheggexercise.ocr.OCRFileReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {QuestionDataSourceScannerTest.MyConfigurationClass.class})
public class QuestionDataSourceScannerTest {

    @Configuration
    static class MyConfigurationClass{
        @Bean
        QuestionDataSourceScanner cheggExerciseQuestionScanner(){
            return  new QuestionDataSourceScannerImpl();
        }
        @Bean
        ImageValidator imageValidator(){
            return  new ImageValidator();
        }
        @Bean
        CsvFileReader csvFileReader(){
            return new CsvFileReaderImpl();
        }
        @Bean
        JsonFileReader jsonFileReader(){
            return Mockito.mock(JsonFileReader.class);
        }
        @Bean
        OCRFileReader ocrFileReader(){
            return Mockito.mock(OCRFileReader.class);
        }
    }
    @Autowired
    private QuestionDataSourceScanner questionDataSourceScanner;
    @Autowired
    private OCRFileReader ocrFileReader;

    @Test
    public void copyRemoteFile() throws IOException {
        Assert.assertNotNull(questionDataSourceScanner);
        questionDataSourceScanner.startReadingQuestionDataSources();
        verify(ocrFileReader).readFile("https://s3.amazonaws.com/chegg-candidate-task/qsnapshot.png");
    }
}
