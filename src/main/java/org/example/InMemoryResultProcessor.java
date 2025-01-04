package org.example;

import org.example.Config.ExaminationsConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InMemoryResultProcessor implements ResultProcessor {
    List <String> onlyRightMarks = new ArrayList<>();

    /**
     * считывает данные с txt-файла и записывает их в list
     * @param * на вход принимает путь к файлу
     * @return лист с оценками
     * @throws IOException
     */

    @Override
    public List<String> parseResultFromFile(String path) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(path));
        List<String> parsedResults = new ArrayList<>();
        String line = bf.readLine();

        while (line != null) {
            String[] split = line.split(" - ");
            parsedResults.add(split[1]);
            line = bf.readLine();
        }
        bf.close();
        return parsedResults;
    }


    // получаем результаты правильных ответов из файла
    @Override
    public List<String> getRightAnswers() throws IOException {
        return parseResultFromFile("D:\\Java\\Examinations\\src\\main\\resources\\right-answers.txt");
    }

    // получаем результаты ответов студента из файла
    @Override
    public List<String> getCurrentAnswers() throws IOException {
        return parseResultFromFile("D:\\Java\\Examinations\\src\\main\\resources\\current-answers.txt");
    }

    /**
     * построчно сравниваем результаты с правильными ответами и заносим их в лист. Где ответы неверны - заносим null,
     * чтобы сохранить индексы ответов
     * @return список с правильными ответами
     * @throws IOException
     */

    @Override
    public List <String> compareResult() throws IOException {
        getCurrentAnswers();
        getRightAnswers();
        for (int i = 0; i < getRightAnswers().size(); i++) {
            if(getCurrentAnswers().get(i).equals(getRightAnswers().get(i))){
                onlyRightMarks.add(getCurrentAnswers().get(i));
            } else onlyRightMarks.add(null);
        }
        return onlyRightMarks;
    }

    /**
     * в цикле прибавляем ключ в зависимости от номера вопроса.
     * @return оценка студента.
     * пока не поучается реализовать, т.к. лист с ключами не заполняется из properties-файла
     */

    @Override
    public int getMark() throws IOException {
        ExaminationsConfig ec = new ExaminationsConfig();
        int mark = 0;
        compareResult();
        for (int i = 0; i < onlyRightMarks.size(); i++) {
            if(onlyRightMarks.get(i) != null && i < 2) {
                mark += ec.keys.get(0);
            }
            if(onlyRightMarks.get(i) != null && i > 4 && i < 7) {
                mark += ec.keys.get(1);
            }
            if(onlyRightMarks.get(i) != null && i > 8) {
                mark += ec.keys.get(2);
            }
        }
        System.out.println("Your grade is: " + mark);
        return mark;
    }
}

