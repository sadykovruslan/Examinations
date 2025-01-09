package org.example;


import java.io.IOException;
import java.util.List;


public interface ResultProcessor {
    List<String> parseResultFromFile(String path) throws IOException;
    void getAllResults();
    void compareResult () throws IOException, InterruptedException;
    int getMark() throws IOException, InterruptedException;
}