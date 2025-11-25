package com.example.patterns.structural.bridge;

/**
 * System alert notification (RefinedAbstraction).
 * 
 * <p>This refined abstraction sends urgent system alerts with
 * high priority formatting.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class SystemAlert extends Notification {
    
    private final String alertLevel;
    private final String systemName;
    private final String errorMessage;
    
    /**
     * Creates a system alert notification.
     *
     * @param sender the message sender to use
     * @param alertLevel the alert severity level (INFO, WARNING, ERROR, CRITICAL)
     * @param systemName the name of the system generating the alert
     * @param errorMessage the error or alert message
     */
    public SystemAlert(MessageSender sender, String alertLevel, 
                      String systemName, String errorMessage) {
        super(sender);
        this.alertLevel = alertLevel;
        this.systemName = systemName;
        this.errorMessage = errorMessage;
    }
    
    @Override
    protected String formatMessage() {
        return String.format("""
            [!] SYSTEM ALERT [%s]
            System: %s
            Message: %s
            Time: %s
            Please investigate immediately.
            """,
            alertLevel,
            systemName,
            errorMessage,
            java.time.LocalDateTime.now()
        );
    }
}
