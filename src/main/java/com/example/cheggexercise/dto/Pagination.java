package com.example.cheggexercise.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"next"})
public class Pagination {

    @JsonProperty("next")
    private List<String> next = new ArrayList<>();

    @JsonProperty("next")
    public List<String> getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(List<String> next) {
        this.next = next;
    }

}