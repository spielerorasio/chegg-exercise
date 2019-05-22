package com.example.cheggexercise.service;

import com.example.cheggexercise.domain.Question;
import com.example.cheggexercise.event.CreateQuestionEvent;
import com.example.cheggexercise.domain.SourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface QuestionService {

    void handleCreateQuestionEvent(CreateQuestionEvent createQuestionEvent) ;

    Question save(Question question);

    void delete(Question question);

    Page<Question> findAll(PageRequest pageRequest);


    Page<Question> findBySourceType(SourceType sourceType, Pageable pageable);

    Page<Question> findByTextContains(String text, Pageable pageable);

    Page<Question> findBySourceAndTextContains(SourceType sourceType, String text, Pageable pageable);

}
