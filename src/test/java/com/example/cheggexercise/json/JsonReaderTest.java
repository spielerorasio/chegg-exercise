package com.example.cheggexercise.json;

import com.example.cheggexercise.event.CreateQuestionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@ContextConfiguration(classes = {JsonReaderTest.MyConfigurationClass.class})
public class JsonReaderTest {

    static AtomicInteger questionsCreated = new AtomicInteger(0);
    @Configuration
    static class MyConfigurationClass implements ApplicationListener<CreateQuestionEvent> {
        @Bean
        JsonFileReader jsonFileReader(){
            return  new JsonFileReaderImpl();
        }
        @Bean
        ObjectMapper objectMapper(){
            return new ObjectMapper();
        }
        @Override
        public void onApplicationEvent(CreateQuestionEvent createQuestionEvent) {
            Assert.assertNotNull(createQuestionEvent.getField());
            Assert.assertNotNull(createQuestionEvent.getField());

            questionsCreated.incrementAndGet();
        }
    }
    @Autowired
    private JsonFileReader jsonFileReader;

    @Test
    public void readJsonFile() throws IOException {
        Assert.assertNotNull(jsonFileReader);
        Assert.assertEquals(0, questionsCreated.get());
        jsonFileReader.readJsonFile("https://s3.amazonaws.com/chegg-candidate-task/data.json");
        Assert.assertNotEquals(0, questionsCreated.get());

    }


}
