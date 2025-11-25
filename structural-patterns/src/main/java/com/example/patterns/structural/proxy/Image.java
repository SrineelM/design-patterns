package com.example.patterns.structural.proxy;

/**
 * Subject interface for image operations.
 * 
 * <p>This is the Subject in the Proxy pattern. It defines the common
 * interface for RealSubject and Proxy so they can be used interchangeably.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public interface Image {
    
    /**
     * Displays the image.
     */
    void display();
    
    /**
     * Gets the image filename.
     *
     * @return the filename
     */
    String getFilename();
    
    /**
     * Gets the image dimensions.
     *
     * @return the dimensions string
     */
    String getDimensions();
    
    /**
     * Gets the file size in bytes.
     *
     * @return the file size
     */
    long getFileSize();
}
