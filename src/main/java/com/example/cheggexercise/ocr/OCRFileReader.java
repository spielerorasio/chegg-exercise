package com.example.cheggexercise.ocr;

import com.example.cheggexercise.FileReader;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface OCRFileReader  extends FileReader {
    String GOOGLE_APPLICATION_API_KEY = "GOOGLE_APPLICATION_API_KEY";

    void readQuestionFromOCR(String url) throws IOException ;

    void readQuestionFromOCR(Path path) throws IOException;

}
