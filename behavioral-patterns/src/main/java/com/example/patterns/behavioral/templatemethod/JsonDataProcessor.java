package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON data processor.
 * 
 * @since 1.0
 */
@Component
public class JsonDataProcessor extends DataProcessor {
    
    private static final Logger log = LoggerFactory.getLogger(JsonDataProcessor.class);
    
    @Override
    protected String loadData() {
        log.info("Loading JSON file...");
        return "[{\"name\":\"John\",\"age\":30},{\"name\":\"Jane\",\"age\":25}]";
    }
    
    @Override
    protected List<String> parseData(String rawData) {
        log.info("Parsing JSON format...");
        // Simplified JSON parsing
        return Arrays.asList(
            "{\"name\":\"John\",\"age\":30}",
            "{\"name\":\"Jane\",\"age\":25}"
        );
    }
    
    @Override
    protected void saveResults(List<String> data) {
        log.info("Saving JSON results to output file...");
        data.forEach(record -> log.info("JSON Record: {}", record));
    }
    
    @Override
    protected String getFormat() {
        return "JSON";
    }
}
