package com.example.cheggexercise.json;

import com.example.cheggexercise.dto.JsonQuestion;
import com.example.cheggexercise.dto.JsonQuestionList;
import com.example.cheggexercise.event.CreateQuestionEvent;
import com.example.cheggexercise.FileReader;
import com.example.cheggexercise.domain.SourceType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Component
public class JsonFileReaderImpl implements JsonFileReader,FileReader {
    private static final Logger logger = LoggerFactory.getLogger(JsonFileReaderImpl.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void readJsonFile(String url) throws IOException {
        Path path = copyRemoteFile(url);
        //TODO find out if we can read the all file to memory - otherwise use Scanner readLine()
        JsonQuestionList jsonQuestionList = objectMapper.readValue(path.toFile(), JsonQuestionList.class);
        if(jsonQuestionList!=null){
            for (JsonQuestion jsonQuestion : jsonQuestionList.getQuestions()) {
                applicationEventPublisher.publishEvent(
                        new CreateQuestionEvent(SourceType.json, jsonQuestion.getField(), jsonQuestion.getText()));
            }
        }
        path.toFile().delete();
    }
}
