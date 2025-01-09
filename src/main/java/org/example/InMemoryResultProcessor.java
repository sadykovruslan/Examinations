package org.example;

import org.example.Config.ExaminationsConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InMemoryResultProcessor implements ResultProcessor {
    private final ExaminationsConfig ec;
    List <String> onlyRightMarks = new ArrayList<>();
    List<String> currentAnswers = new ArrayList<>();
    List<String> rightAnswers = new ArrayList<>();

    @Autowired
    public InMemoryResultProcessor(ExaminationsConfig ec) {
        this.ec = ec;
    }

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

    @Override
    public void getAllResults(){
        Thread right = new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    rightAnswers = parseResultFromFile("D:\\Java\\Examinations\\src\\main\\resources\\right-answers.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread current = new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    currentAnswers = parseResultFromFile("D:\\Java\\Examinations\\src\\main\\resources\\current-answers.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        right.start();
        current.start();
    }

    /**
     * Построчно сравниваем результаты с правильными ответами и заносим их в лист. Где ответы неверны - заносим null,
     * чтобы сохранить индексы ответов
     *
     * @throws IOException
     */

    @Override
    public void compareResult() throws IOException, InterruptedException {
        getAllResults();
        Thread.sleep(1000);
        for (int i = 0; i < rightAnswers.size(); i++) {
            if (currentAnswers.get(i).equals(rightAnswers.get(i))){
                onlyRightMarks.add(currentAnswers.get(i));
            } else onlyRightMarks.add(null);
        }
    }

    /**
     * в цикле прибавляем ключ в зависимости от номера вопроса.
     * @return оценка студента.
     */


    @Override
    public int getMark() throws IOException, InterruptedException {
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

