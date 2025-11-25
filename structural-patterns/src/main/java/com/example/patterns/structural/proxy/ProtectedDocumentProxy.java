package com.example.patterns.structural.proxy;

import com.example.patterns.common.DesignPattern;

import java.util.Set;

/**
 * Protection proxy for document access control (Proxy).
 * 
 * <p>This is a Protection Proxy - it controls access to the RealDocument
 * based on user permissions. Only authorized users can perform certain operations.
 * 
 * <p><b>Benefits:</b>
 * <ul>
 *   <li>Enforces access control</li>
 *   <li>Separates security concerns from business logic</li>
 *   <li>Can log access attempts</li>
 * </ul>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
@DesignPattern(
    name = "Proxy (Protection)",
    category = "Structural",
    description = "Controls access to documents based on user permissions"
)
public class ProtectedDocumentProxy implements Document {
    
    private final RealDocument realDocument;
    private final String currentUser;
    private final Set<String> authorizedEditors;
    private final Set<String> authorizedDeleters;
    
    /**
     * Creates a protected document proxy.
     *
     * @param filename the document filename
     * @param currentUser the current user
     * @param authorizedEditors users who can edit
     * @param authorizedDeleters users who can delete
     */
    public ProtectedDocumentProxy(String filename, String currentUser,
                                 Set<String> authorizedEditors,
                                 Set<String> authorizedDeleters) {
        this.realDocument = new RealDocument(filename);
        this.currentUser = currentUser;
        this.authorizedEditors = authorizedEditors;
        this.authorizedDeleters = authorizedDeleters;
        
        System.out.printf("[PROTECTION PROXY] Created for user: %s%n", currentUser);
    }
    
    @Override
    public void view() {
        // Everyone can view
        System.out.printf("[PROTECTION PROXY] User '%s' viewing document%n", currentUser);
        realDocument.view();
    }
    
    @Override
    public void edit(String content) {
        // Check edit permission
        if (!authorizedEditors.contains(currentUser)) {
            System.out.printf("[PROTECTION PROXY] ACCESS DENIED: User '%s' cannot edit%n", 
                currentUser);
            throw new SecurityException("User not authorized to edit");
        }
        
        System.out.printf("[PROTECTION PROXY] User '%s' authorized to edit%n", currentUser);
        realDocument.edit(content);
    }
    
    @Override
    public void delete() {
        // Check delete permission
        if (!authorizedDeleters.contains(currentUser)) {
            System.out.printf("[PROTECTION PROXY] ACCESS DENIED: User '%s' cannot delete%n", 
                currentUser);
            throw new SecurityException("User not authorized to delete");
        }
        
        System.out.printf("[PROTECTION PROXY] User '%s' authorized to delete%n", currentUser);
        realDocument.delete();
    }
}
