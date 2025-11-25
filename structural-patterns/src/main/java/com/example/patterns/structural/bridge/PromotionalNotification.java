package com.example.patterns.structural.bridge;

/**
 * Marketing promotion notification (RefinedAbstraction).
 * 
 * <p>This refined abstraction sends promotional messages with
 * marketing-friendly formatting.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class PromotionalNotification extends Notification {
    
    private final String productName;
    private final int discountPercent;
    private final String promoCode;
    
    /**
     * Creates a promotional notification.
     *
     * @param sender the message sender to use
     * @param productName the product being promoted
     * @param discountPercent the discount percentage
     * @param promoCode the promotional code
     */
    public PromotionalNotification(MessageSender sender, String productName,
                                  int discountPercent, String promoCode) {
        super(sender);
        this.productName = productName;
        this.discountPercent = discountPercent;
        this.promoCode = promoCode;
    }
    
    @Override
    protected String formatMessage() {
        return String.format("""
            *** SPECIAL OFFER! ***
            
            Get %d%% OFF on %s!
            
            Use code: %s at checkout
            
            Limited time offer. Shop now!
            """,
            discountPercent,
            productName,
            promoCode
        );
    }
}
