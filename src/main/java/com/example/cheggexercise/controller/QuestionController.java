package com.example.cheggexercise.controller;

import com.example.cheggexercise.domain.Question;
import com.example.cheggexercise.domain.SourceType;
import com.example.cheggexercise.dto.QuestionDetails;
import com.example.cheggexercise.dto.QuestionList;
import com.example.cheggexercise.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@RestController
@RequestMapping("/rest")
public class QuestionController {

    @Autowired
    QuestionService questionService;



    @RequestMapping(value = "/find/source/{sourceType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public QuestionList findBySourceType(@PathVariable("sourceType") String sourceType,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "20") int size) {

        page = (page<0)? 0: page;
        size = (size<1)? 20: size;

        SourceType type = SourceType.valueOf(sourceType.toLowerCase());

        Page<Question> bySourceType = questionService.findBySourceType(type, PageRequest.of(page,size));
        QuestionList questionList = new QuestionList();
        if(bySourceType!=null){
            for (Question question : bySourceType) {
                questionList.getQuestions().add(new QuestionDetails(question.getSourceType().toString(), question.getField(), question.getText()));
            }
            String next = "/rest/find/source/" + sourceType + "?page=" + (page + 1) + "&size=" + size;
            questionList.getPagination().setNext(Collections.singletonList(next));
        }
        return questionList;
    }

    @RequestMapping(value = "/find/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public QuestionList findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "20") int size) {

        page = (page<0)? 0: page;
        size = (size<1)? 20: size;
        Page<Question> bySourceType;
        bySourceType = questionService.findAll(PageRequest.of(page,size));
        QuestionList questionList = new QuestionList();
        if(bySourceType!=null){
            for (Question question : bySourceType) {
                questionList.getQuestions().add(new QuestionDetails(question.getSourceType().toString(), question.getField(), question.getText()));
            }
            String next = "/rest/find/all?page=" + (page + 1) + "&size=" + size;
            questionList.getPagination().setNext(Collections.singletonList(next));
        }
        return questionList;
    }

    @RequestMapping(value = "/find/text/{text}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public QuestionList findByText(@PathVariable("text") String text,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "20") int size) {

        page = (page<0)? 0: page;
        size = (size<1)? 20: size;

        Page<Question> bySourceType = questionService.findByTextContains(text, PageRequest.of(page,size));

        QuestionList questionList = new QuestionList();
        if(bySourceType!=null){
            for (Question question : bySourceType) {
                questionList.getQuestions().add(new QuestionDetails(question.getSourceType().toString(), question.getField(), question.getText()));
            }
            String next = "/rest/find/text/" + text + "?page=" + (page + 1) + "&size=" + size;
            questionList.getPagination().setNext(Collections.singletonList(next));
        }
        return questionList;
    }


    @RequestMapping(value = "/find/source/{sourceType}/text/{text}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public QuestionList findByTextAndSource(@PathVariable("sourceType") String sourceType,
                                            @PathVariable("text") String text,
                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "20") int size) {

        page = (page<0)? 0: page;
        size = (size<1)? 20: size;
        SourceType type = SourceType.valueOf(sourceType.toLowerCase());

        Page<Question> bySourceType = questionService.findBySourceAndTextContains(type, text, PageRequest.of(page,size));
        QuestionList questionList = new QuestionList();
        if(bySourceType!=null){
            for (Question question : bySourceType) {
                questionList.getQuestions().add(new QuestionDetails(question.getSourceType().toString(), question.getField(), question.getText()));
            }
            String next = "/rest/find/source/" + sourceType + "/text/" + text + "?page=" + (page + 1) + "&size=" + size;

            questionList.getPagination().setNext(Collections.singletonList(next));
        }
        return questionList;
    }
}
