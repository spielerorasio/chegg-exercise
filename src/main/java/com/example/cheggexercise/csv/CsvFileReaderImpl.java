package com.example.cheggexercise.csv;

import com.example.cheggexercise.event.CreateQuestionEvent;
import com.example.cheggexercise.FileReader;
import com.example.cheggexercise.domain.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Component
public class CsvFileReaderImpl implements CsvFileReader,FileReader {
    private static final Logger logger = LoggerFactory.getLogger(CsvFileReaderImpl.class);

    private static final String ID = "id";

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private static final String COMMA = ",";

    @Override
    public void readCsvFile(String url) throws IOException {
        Path path = copyRemoteFile(url);
        //TODO find out if we can read the all file to memory - otherwise use Scanner readLine()
        List<String> list = Files.readAllLines(path);
        for (String line : list) {
            logger.debug("csv next line {}",line);
            String[] values = line.split(COMMA);
            if(ID.equalsIgnoreCase(values[0])) {
                continue;
            }
            applicationEventPublisher.publishEvent(new CreateQuestionEvent(SourceType.csv, values[2], values[1]));
        }
        path.toFile().delete();
    }
}
