package com.example.cheggexercise.ocr;

import com.example.cheggexercise.event.CreateQuestionEvent;
import org.apache.logging.log4j.util.Strings;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OCRReaderTest.MyConfigurationClass.class})
public class OCRReaderTest {

    static AtomicInteger questionsCreated = new AtomicInteger(0);
    @Configuration
    static class MyConfigurationClass implements ApplicationListener<CreateQuestionEvent> {
        @Bean
        OCRFileReader ocrFileReader(){
            return  new OCRFileReaderImpl();
        }

        @Override
        public void onApplicationEvent(CreateQuestionEvent createQuestionEvent) {
            Assert.assertNotNull(createQuestionEvent.getField());
            Assert.assertNotNull(createQuestionEvent.getField());

            questionsCreated.incrementAndGet();
        }
    }
    @Autowired
    private OCRFileReader ocrFileReader;

    @Test
    public void readOcrFile() throws IOException {
        Assert.assertNotNull(ocrFileReader);
        if(Strings.isEmpty(System.getenv(OCRFileReader.GOOGLE_APPLICATION_API_KEY))) {
            System.out.println("Missing "+OCRFileReader.GOOGLE_APPLICATION_API_KEY);
            return;
        }
        Path path = Paths.get("src","test","resources","qsnapshot.png");

        Assert.assertEquals(0, questionsCreated.get());

        ocrFileReader.readQuestionFromOCR(path);
//        ocrFileReader.readQuestionFromOCR("https://s3.amazonaws.com/chegg-candidate-task/qsnapshot.png");
        Assert.assertNotEquals(0, questionsCreated.get());

    }


}
