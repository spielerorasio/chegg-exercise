package com.example.cheggexercise.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Document(indexName = "student_questions", type = "question")
public class Question {
    @Id
    private String id;


    @Field(type = FieldType.Keyword)
    private String field;

    //A field to index full-text values
    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Keyword)
    private SourceType sourceType;

    public Question() {
    }

    public Question(SourceType sourceType, String field, String text) {
        this.sourceType = sourceType;
        this.field = field;
        this.text = text;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(field, question.field) &&
                Objects.equals(text, question.text) &&
                sourceType == question.sourceType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(field, text, sourceType);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", field='" + field + '\'' +
                ", text='" + text + '\'' +
                ", sourceType=" + sourceType +
                '}';
    }
}
