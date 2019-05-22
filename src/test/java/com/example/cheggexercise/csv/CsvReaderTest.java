package com.example.cheggexercise.csv;

import com.example.cheggexercise.event.CreateQuestionEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CsvReaderTest.MyConfigurationClass.class})
public class CsvReaderTest  {

    static AtomicInteger questionsCreated = new AtomicInteger(0);
    @Configuration
//    @PropertySource()
    static class MyConfigurationClass implements ApplicationListener<CreateQuestionEvent> {
        @Bean
        CsvFileReaderImpl csvFileReader(){
            return  new CsvFileReaderImpl();
        }

        @Override
        public void onApplicationEvent(CreateQuestionEvent createQuestionEvent) {
            Assert.assertNotNull(createQuestionEvent.getField());
            Assert.assertNotNull(createQuestionEvent.getField());
            questionsCreated.incrementAndGet();
        }
    }
    @Autowired
    private CsvFileReaderImpl csvFileReader;

    @Test
    public void readCsvFile() throws IOException {
        Assert.assertNotNull(csvFileReader);
        Assert.assertEquals(0, questionsCreated.get());
        csvFileReader.readCsvFile("https://s3.amazonaws.com/chegg-candidate-task/questions.csv");
        Assert.assertNotEquals(0, questionsCreated.get());

    }


}
