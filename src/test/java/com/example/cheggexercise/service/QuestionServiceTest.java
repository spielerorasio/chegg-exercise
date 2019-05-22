package com.example.cheggexercise.service;


import com.example.cheggexercise.domain.Question;
import com.example.cheggexercise.domain.SourceType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {


    @Autowired
    private QuestionService questionService;


    @Test
    public void testService() throws InterruptedException {
        Assert.assertNotNull(questionService);
        TimeUnit.SECONDS.sleep(3);

        Question question = new Question(SourceType.csv, "field1 DSF abc", "text1");

        questionService.save(question);
        Page<Question> all = questionService.findAll(PageRequest.of(0, 1));
        Assert.assertFalse(all.getContent().isEmpty());
        Page<Question> page = questionService.findBySourceType(SourceType.csv, PageRequest.of(0,20));
        Assert.assertFalse(page.isEmpty());
        page = questionService.findByTextContains("dsf", PageRequest.of(0, 1));
        Assert.assertTrue(page.isEmpty());

        questionService.delete(question);

    }

}
