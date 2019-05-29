package com.example.cheggexercise.csv;

import com.example.cheggexercise.FileReader;

import java.io.IOException;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface CsvFileReader extends FileReader {
    void readCsvFile(String url) throws IOException;
}
