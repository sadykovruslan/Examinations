package org.example.Config;

import org.example.InMemoryResultProcessor;
import org.example.ResultProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import(PropertiesConfiguration.class)
public class ExaminationsConfig {

    @Value("#{'${keys}'.split(',')}")
    public List<Integer> keys;


    @Bean
    public ResultProcessor resultProcessor() {
        return new InMemoryResultProcessor();
    }



}
