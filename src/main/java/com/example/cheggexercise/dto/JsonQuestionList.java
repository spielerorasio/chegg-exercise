package com.example.cheggexercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"questions"})
public class JsonQuestionList {

    @JsonProperty("questions")
    private List<JsonQuestion> questions = null;

    @JsonProperty("questions")
    public List<JsonQuestion> getQuestions() {
        return questions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<JsonQuestion> questions) {
        this.questions = questions;
    }


}
