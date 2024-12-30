package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.example.Config.ExaminationsConfig;

public class InMemoryResultProcessor implements ResultProcessor {
    ExaminationsConfig ec = new ExaminationsConfig();
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
     * чтобы сохранить интекды ответов
     * @return список с правильнымии ответами
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
     * в каждом цикле прибавляем ключ из properties-файла.
     * @return оценка студента.
     * пока не поучается реализовать, т.к. лист с ключами не заполняется из properties-файла
     * @throws IOException
     */

    @Override
    public int getMark() throws IOException {
        int mark = 0;
        compareResult();
        for (int i = 0; i < 3; i++) {
            if(onlyRightMarks.get(i) !=null) {
                mark +=ec.current.get(0);
            }
        }

        for (int i = 4; i < 7; i++) {
            if(onlyRightMarks.get(i) !=null) {
                mark +=ec.current.get(1);
            }
        }

        for (int i = 8; i < 9; i++) {
            if(onlyRightMarks.get(i) !=null) {
                mark +=ec.current.get(2);
            }
        }
        System.out.println("Your grade is: " + mark);
        return mark;
    }
}

