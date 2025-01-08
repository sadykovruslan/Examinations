package org.example;

import org.example.Config.ExaminationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryResultProcessorTest {

    ExaminationsConfig ec;
    InMemoryResultProcessor irp = new InMemoryResultProcessor(ec);
    String path = "D:\\Java\\Examinations\\src\\main\\resources\\right-answers.txt";

//    public InMemoryResultProcessorTest(InMemoryResultProcessor irp, ExaminationsConfig ec, String path) {
//        this.irp = irp;
//        this.ec = ec;
//        this.path = path;
//    }


    @Test
    void parseResultFromFile() throws IOException {
            Assertions.assertEquals(10, irp.parseResultFromFile(path).size());
            Assertions.assertEquals("Б,", irp.parseResultFromFile(path).get(1));
            Assertions.assertTrue(irp.parseResultFromFile(path).contains("В,"), "В,");
    }

     // методы по получению результатов идентичны, поэтому тест один
    @Test
    void getAnswers() throws IOException {
        Assertions.assertEquals(10, irp.parseResultFromFile(path).size());
        Assertions.assertEquals("Б,", irp.parseResultFromFile(path).get(1));
        Assertions.assertTrue(irp.parseResultFromFile(path).contains("В,"), "В,");
    }

    @Test
    void getMark() throws IOException {
        Assertions.assertEquals(4, irp.getMark());
    }
}