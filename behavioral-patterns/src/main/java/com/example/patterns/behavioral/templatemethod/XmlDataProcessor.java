package com.example.patterns.behavioral.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * XML data processor.
 * 
 * @since 1.0
 */
@Component
public class XmlDataProcessor extends DataProcessor {
    
    private static final Logger log = LoggerFactory.getLogger(XmlDataProcessor.class);
    
    @Override
    protected String loadData() {
        log.info("Loading XML file...");
        return "<users><user><name>John</name></user><user><name>Jane</name></user></users>";
    }
    
    @Override
    protected List<String> parseData(String rawData) {
        log.info("Parsing XML format...");
        return Arrays.asList(
            "<user><name>John</name></user>",
            "<user><name>Jane</name></user>"
        );
    }
    
    @Override
    protected void saveResults(List<String> data) {
        log.info("Saving XML results to output file...");
        data.forEach(record -> log.info("XML Record: {}", record));
    }
    
    @Override
    protected String getFormat() {
        return "XML";
    }
    
    @Override
    protected boolean validateData(String data) {
        // XML-specific validation
        if (!super.validateData(data)) {
            return false;
        }
        if (!data.contains("<") || !data.contains(">")) {
            log.warn("Invalid XML: no tags found");
            return false;
        }
        log.info("XML structure validation passed");
        return true;
    }
}
