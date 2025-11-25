package com.example.patterns.structural.proxy;

import com.example.patterns.common.DesignPattern;

/**
 * Virtual proxy for lazy loading images (Proxy).
 * 
 * <p>This is a Virtual Proxy - it defers the creation of the expensive
 * RealImage object until it's actually needed. The proxy stores metadata
 * and only loads the full image when display() is called.
 * 
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Reduces initial loading time</li>
 *   <li>Saves memory for images that are never displayed</li>
 *   <li>Transparent to clients - same interface as RealImage</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Proxy (Virtual)",
    category = "Structural",
    description = "Provides lazy loading for expensive image objects"
)
public class ImageProxy implements Image {
    
    private final String filename;
    private RealImage realImage;
    
    /**
     * Creates an image proxy.
     * 
     * <p>Note: The real image is NOT loaded here. This is instant.
     *
     * @param filename the image filename
     */
    public ImageProxy(String filename) {
        this.filename = filename;
        System.out.printf("[PROXY] Created proxy for '%s' (no loading yet)%n", filename);
    }
    
    @Override
    public void display() {
        // Lazy loading: create real image only when needed
        if (realImage == null) {
            System.out.println("[PROXY] First display() call - loading real image now");
            realImage = new RealImage(filename);
        } else {
            System.out.println("[PROXY] Using already-loaded image");
        }
        
        // Delegate to real image
        realImage.display();
    }
    
    @Override
    public String getFilename() {
        return filename;
    }
    
    @Override
    public String getDimensions() {
        // Can return metadata without loading full image
        return "1920x1080";
    }
    
    @Override
    public long getFileSize() {
        // Can return metadata without loading full image
        return 2048576;
    }
    
    /**
     * Checks if the real image has been loaded.
     *
     * @return true if loaded, false otherwise
     */
    public boolean isLoaded() {
        return realImage != null;
    }
}
