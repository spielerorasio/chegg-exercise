package com.example.cheggexercise.service;

import com.example.cheggexercise.domain.Question;
import com.example.cheggexercise.domain.SourceType;
import com.example.cheggexercise.event.CreateQuestionEvent;
import com.example.cheggexercise.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    QuestionRepository questionRepository;

    @Override
    @EventListener
    public void handleCreateQuestionEvent(CreateQuestionEvent createQuestionEvent) {
        logger.info("Found a new {} question - creating en event", createQuestionEvent.getSourceType());
        save(new Question(createQuestionEvent.getSourceType(),createQuestionEvent.getField(), createQuestionEvent.getText()));
    }

    @Override
    public Question save(Question question) {
        logger.debug("creating a new question {}", question);
        return questionRepository.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }


    @Override
    public Page<Question> findAll(PageRequest pageRequest) {
        return questionRepository.findAll(pageRequest);
    }

    @Override
    public Page<Question> findBySourceType(SourceType sourceType, Pageable pageable){
        return questionRepository.findBySourceType(sourceType, pageable);
    }

    @Override
    public Page<Question> findByTextContains(String text, Pageable pageable){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(matchPhraseQuery("text", text.toLowerCase()).slop(3))
                .build();
        return questionRepository.search(searchQuery);
    }

    @Override
    public Page<Question> findBySourceAndTextContains(SourceType sourceType, String text, Pageable pageable){
        return questionRepository.findBySourceTypeAndTextLike(sourceType, text, pageable);

    }



}
