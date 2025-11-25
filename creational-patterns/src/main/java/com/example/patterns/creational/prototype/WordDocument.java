package com.example.patterns.creational.prototype;

import com.example.patterns.common.DesignPattern;
import java.util.*;

/**
 * Concrete Word Document Implementation
 * 
 * This class demonstrates the Prototype pattern by implementing Cloneable
 * and providing a deep copy implementation.
 * 
 * Each document has:
 * - Content (the text)
 * - Metadata (creation date, author, revision count)
 * - Formatting (styles, fonts)
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Prototype",
    category = "Creational",
    description = "Create new objects by copying existing prototype"
)
public class WordDocument implements Document {
    
    private String content;
    private String author;
    private Date createdDate;
    private int revisionCount;
    private Map<String, String> metadata;
    
    /**
     * Default constructor for Word documents.
     */
    public WordDocument() {
        this.content = "";
        this.author = "Unknown";
        this.createdDate = new Date();
        this.revisionCount = 0;
        this.metadata = new HashMap<>();
    }
    
    /**
     * Creates a deep copy of this Word document.
     * 
     * This implementation performs a complete deep copy including:
     * - Content string
     * - Author information
     * - Creation date
     * - Revision count
     * - All metadata entries
     * 
     * @return a new independent copy of this document
     * @throws CloneNotSupportedException should not occur
     */
    @Override
    public Document clone() throws CloneNotSupportedException {
        // Shallow clone first (handles immutable fields and references)
        WordDocument cloned = (WordDocument) super.clone();
        
        // Deep copy of mutable fields
        cloned.content = new String(this.content);
        cloned.author = new String(this.author);
        cloned.createdDate = new Date(this.createdDate.getTime());
        cloned.metadata = new HashMap<>(this.metadata);
        
        // Reset revision counter for cloned document
        cloned.revisionCount = 0;
        
        return cloned;
    }
    
    @Override
    public String getContent() {
        return content;
    }
    
    @Override
    public void setContent(String content) {
        this.content = content;
        this.revisionCount++;
    }
    
    @Override
    public String getType() {
        return "Word Document";
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Date getCreatedDate() {
        return new Date(createdDate.getTime());
    }
    
    public int getRevisionCount() {
        return revisionCount;
    }
    
    public void setMetadata(String key, String value) {
        metadata.put(key, value);
    }
    
    public String getMetadata(String key) {
        return metadata.get(key);
    }
    
    @Override
    public String toString() {
        return "WordDocument{" +
                "type='" + getType() + "'" +
                ", author='" + author + "'" +
                ", revisions=" + revisionCount +
                ", contentLength=" + content.length() +
                ", created=" + createdDate +
                '}';
    }
}
