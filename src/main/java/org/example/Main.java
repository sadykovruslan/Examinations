package org.example;

import org.example.Config.ExaminationsConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext examinationsConfig = new AnnotationConfigApplicationContext(ExaminationsConfig.class);

        var resultProcessor = examinationsConfig.getBean(ResultProcessor.class);
        resultProcessor.getMark();

//        InMemoryResultProcessor rp = new InMemoryResultProcessor();
//        rp.getMark();



    }
}