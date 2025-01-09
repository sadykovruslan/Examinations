package org.example;

import org.example.Config.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import(PropertiesConfigurationTest.class)
public class ExaminationsConfigTest {

    @Value("#{'${keys}'.split(',')}")
    public List<Integer> keys;


//    @Bean
//    public InMemoryResultProcessorTest resultProcessor() {
//        return new InMemoryResultProcessorTest(this);
//    }
}
