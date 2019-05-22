package com.example.cheggexercise.repository;

import com.example.cheggexercise.domain.Question;
import com.example.cheggexercise.domain.SourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface QuestionRepository extends ElasticsearchRepository<Question, String> {
    Page<Question> findBySourceType(SourceType sourceType, Pageable pageable);
    Page<Question> findBySourceTypeAndTextLike(SourceType sourceType, String text, Pageable pageable);
    Page<Question> findByTextLike(String text, Pageable pageable);
}
