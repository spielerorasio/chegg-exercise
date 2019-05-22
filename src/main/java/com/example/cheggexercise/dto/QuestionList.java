package com.example.cheggexercise.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"questions", "pagination"})
public class QuestionList {

    @JsonProperty("questions")
    private List<QuestionDetails> questions = new ArrayList<>();

    @JsonProperty("pagination")
    private Pagination pagination = new Pagination();

    @JsonProperty("questions")
    public List<QuestionDetails> getQuestions() {
        return questions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<QuestionDetails> questions) {
        this.questions = questions;
    }

    @JsonProperty("pagination")
    public Pagination getPagination() {
        return pagination;
    }

    @JsonProperty("pagination")
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
