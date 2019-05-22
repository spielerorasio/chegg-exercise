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
@JsonPropertyOrder({"pages", "text"})
public class OCRFullTextAnnotation {

    @JsonProperty("pages")
    private List<Object> pages = null;
    @JsonProperty("text")
    private String text;

    @JsonProperty("pages")
    public List<Object> getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(List<Object> pages) {
        this.pages = pages;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

}
