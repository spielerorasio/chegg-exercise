
package com.example.cheggexercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"fullTextAnnotation"})
public class OCRResponse {

    @JsonProperty("fullTextAnnotation")
    private OCRFullTextAnnotation fullTextAnnotation;

    @JsonProperty("fullTextAnnotation")
    public OCRFullTextAnnotation getFullTextAnnotation() {
        return fullTextAnnotation;
    }

    @JsonProperty("fullTextAnnotation")
    public void setFullTextAnnotation(OCRFullTextAnnotation fullTextAnnotation) {
        this.fullTextAnnotation = fullTextAnnotation;
    }


}