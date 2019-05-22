package com.example.cheggexercise.event;

import com.example.cheggexercise.domain.SourceType;
import org.springframework.context.ApplicationEvent;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public class CreateQuestionEvent extends ApplicationEvent {
    private String field;
    private String text;
    private SourceType sourceType;

    public CreateQuestionEvent(SourceType sourceType, String field, String text) {
        super(field);
        this.field = field;
        this.text = text;
        this.sourceType = sourceType;
    }

    public String getField() {
        return field;
    }

    public String getText() {
        return text;
    }

    public SourceType getSourceType() {
        return sourceType;
    }
}
