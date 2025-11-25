package com.example.patterns.behavioral.templatemethod;

import com.example.patterns.common.DesignPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Abstract template for data processing.
 * 
 * <p>This is the Template Method pattern - it defines the skeleton of an algorithm
 * with some steps implemented and others left abstract for subclasses.</p>
 * 
 * <h3>Template Method Structure:</h3>
 * <pre>
 * process() {              <- Template method (final, can't override)
 *     loadData()           <- Abstract (must implement)
 *     validateData()       <- Concrete (can use as-is)
 *     parseData()          <- Abstract (must implement)
 *     transformData()      <- Hook (can override)
 *     saveResults()        <- Abstract (must implement)
 * }
 * </pre>
 * 
 * @since 1.0
 */
@DesignPattern(
    name = "Template Method",
    category = "Behavioral",
    description = "Defines algorithm skeleton, letting subclasses override specific steps"
)
public abstract class DataProcessor {
    
    private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);
    
    /**
     * Template method that defines the data processing algorithm.
     * 
     * <p>This method is final to prevent subclasses from changing the algorithm
     * structure. Subclasses can only customize specific steps.</p>
     * 
     * <h3>Processing Steps:</h3>
     * <ol>
     *   <li>Load raw data from source</li>
     *   <li>Validate the data</li>
     *   <li>Parse into structured format</li>
     *   <li>Transform (optional hook)</li>
     *   <li>Save results</li>
     * </ol>
     */
    public final void process() {
        log.info("=== Starting {} data processing ===", getFormat());
        
        // Step 1: Load data (abstract - subclass must implement)
        String rawData = loadData();
        log.info("Loaded {} characters of raw data", rawData.length());
        
        // Step 2: Validate (concrete - same for all processors)
        if (!validateData(rawData)) {
            log.error("Data validation failed!");
            return;
        }
        
        // Step 3: Parse (abstract - format-specific)
        List<String> parsedData = parseData(rawData);
        log.info("Parsed {} records", parsedData.size());
        
        // Step 4: Transform (hook - optional customization)
        List<String> transformedData = transformData(parsedData);
        log.info("Transformed {} records", transformedData.size());
        
        // Step 5: Save (abstract - format-specific)
        saveResults(transformedData);
        
        log.info("=== Completed {} data processing ===", getFormat());
    }
    
    /**
     * Loads raw data from source. Must be implemented by subclasses.
     * 
     * @return The raw data string
     */
    protected abstract String loadData();
    
    /**
     * Parses raw data into structured format. Format-specific.
     * 
     * @param rawData The raw data to parse
     * @return List of parsed records
     */
    protected abstract List<String> parseData(String rawData);
    
    /**
     * Saves the processed results. Format-specific.
     * 
     * @param data The data to save
     */
    protected abstract void saveResults(List<String> data);
    
    /**
     * Gets the format name for logging.
     * 
     * @return The format name
     */
    protected abstract String getFormat();
    
    /**
     * Validates data. This is concrete - same for all processors.
     * 
     * <p>Subclasses use this as-is or can override if needed.</p>
     * 
     * @param data The data to validate
     * @return true if valid
     */
    protected boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            log.warn("Data is null or empty");
            return false;
        }
        log.info("Data validation passed");
        return true;
    }
    
    /**
     * Hook method for data transformation.
     * 
     * <p>This is a "hook" - it has a default implementation that does nothing,
     * but subclasses can override it to add custom behavior.</p>
     * 
     * @param data The data to transform
     * @return Transformed data (default: returns input unchanged)
     */
    protected List<String> transformData(List<String> data) {
        log.debug("No transformation applied (using default hook)");
        return data;
    }
}
