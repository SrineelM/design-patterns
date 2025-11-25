package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV data processor.
 * 
 * <p>Implements CSV-specific steps of the template algorithm.</p>
 * 
 * @since 1.0
 */
@Component
public class CsvDataProcessor extends DataProcessor {
    
    private static final Logger log = LoggerFactory.getLogger(CsvDataProcessor.class);
    
    @Override
    protected String loadData() {
        log.info("Loading CSV file...");
        // Simulate loading CSV data
        return "name,age,city\nJohn,30,NYC\nJane,25,LA\nBob,35,Chicago";
    }
    
    @Override
    protected List<String> parseData(String rawData) {
        log.info("Parsing CSV format...");
        return Arrays.stream(rawData.split("\n"))
                .skip(1) // Skip header
                .collect(Collectors.toList());
    }
    
    @Override
    protected void saveResults(List<String> data) {
        log.info("Saving CSV results to output file...");
        data.forEach(record -> log.info("CSV Record: {}", record));
    }
    
    @Override
    protected String getFormat() {
        return "CSV";
    }
    
    @Override
    protected List<String> transformData(List<String> data) {
        log.info("Applying CSV-specific transformation: converting to uppercase");
        return data.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
