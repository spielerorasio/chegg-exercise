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
@JsonPropertyOrder({"responses"})
public class OCRResponses {


    @JsonProperty("responses")
    private List<OCRResponse> responses = null;

    @JsonProperty("responses")
    public List<OCRResponse> getResponses() {
        return responses;
    }

    @JsonProperty("responses")
    public void setResponses(List<OCRResponse> responses) {
        this.responses = responses;
    }


}
