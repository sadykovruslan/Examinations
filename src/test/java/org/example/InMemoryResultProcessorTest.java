package org.example;

import org.example.Config.ExaminationsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = {ExaminationsConfigTest.class, PropertiesConfigurationTest.class})
public class InMemoryResultProcessorTest {

    @Autowired
    private ExaminationsConfigTest ec;
    private final InMemoryResultProcessor irp;
    String path = "D:\\Java\\Examinations\\src\\main\\resources\\right-answers.txt";


    public InMemoryResultProcessorTest(@Autowired ExaminationsConfigTest ec) {
        this.ec = ec;
        this.irp = new InMemoryResultProcessor(ec);
    }

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
    void getMark() throws IOException, InterruptedException {
        Assertions.assertEquals(4, irp.getMark());
    }
}