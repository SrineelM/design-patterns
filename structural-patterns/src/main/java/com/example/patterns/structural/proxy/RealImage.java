package com.example.patterns.structural.proxy;

/**
 * Real image implementation (RealSubject).
 * 
 * <p>This is the RealSubject in the Proxy pattern. It represents the
 * real object that does the actual work. Loading images from disk is
 * an expensive operation.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class RealImage implements Image {
    
    private final String filename;
    private final String dimensions;
    private final long fileSize;
    private byte[] imageData;
    
    /**
     * Creates and loads a real image.
     * 
     * <p>This constructor simulates an expensive loading operation.
     *
     * @param filename the image filename
     */
    public RealImage(String filename) {
        this.filename = filename;
        this.dimensions = "1920x1080";
        this.fileSize = 2048576; // 2MB
        
        loadFromDisk();
    }
    
    /**
     * Simulates loading image from disk (expensive operation).
     */
    private void loadFromDisk() {
        System.out.printf("[REAL IMAGE] Loading '%s' from disk...%n", filename);
        
        // Simulate expensive I/O operation
        try {
            Thread.sleep(1000); // 1 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate loading data
        this.imageData = new byte[(int) fileSize];
        
        System.out.printf("[REAL IMAGE] Loaded '%s' (%d bytes)%n", filename, fileSize);
    }
    
    @Override
    public void display() {
        System.out.printf("[REAL IMAGE] Displaying '%s' [%s]%n", 
            filename, dimensions);
    }
    
    @Override
    public String getFilename() {
        return filename;
    }
    
    @Override
    public String getDimensions() {
        return dimensions;
    }
    
    @Override
    public long getFileSize() {
        return fileSize;
    }
    
    @Override
    public String getInfo() {
        return String.format("Image: %s, Size: %d bytes, Dimensions: %s", 
            filename, fileSize, dimensions);
    }
}
