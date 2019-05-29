package com.example.cheggexercise.json;

import com.example.cheggexercise.FileReader;

import java.io.IOException;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface JsonFileReader extends FileReader {
    void readJsonFile(String url) throws IOException ;
}
