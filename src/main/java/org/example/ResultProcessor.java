package org.example;


import java.io.IOException;
import java.util.List;


public interface ResultProcessor {
    List<String> parseResultFromFile(String path) throws IOException;
    void getAllResults();
    List<String> getRightAnswers() throws IOException;
    List<String> getCurrentAnswers() throws IOException;
    List <String> compareResult () throws IOException;
    int getMark() throws IOException;
}