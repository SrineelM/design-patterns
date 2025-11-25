package com.example.patterns.structural.facade;

/**
 * Shipping and fulfillment subsystem.
 * 
 * <p>This subsystem handles order shipping and delivery.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class ShippingService {
    
    /**
     * Validates a shipping address.
     *
     * @param address the shipping address
     * @return true if valid, false otherwise
     */
    public boolean validateAddress(String address) {
        System.out.printf("[SHIPPING] Validating address: %s%n", address);
        boolean valid = address != null && !address.isBlank();
        System.out.printf("[SHIPPING] Address is %s%n", 
            valid ? "VALID" : "INVALID");
        return valid;
    }
    
    /**
     * Calculates shipping cost based on address and weight.
     *
     * @param address the shipping address
     * @param weight the package weight in kg
     * @return the shipping cost
     */
    public double calculateShipping(String address, double weight) {
        System.out.printf("[SHIPPING] Calculating shipping for %.2f kg to: %s%n",
            weight, address);
        double cost = 5.00 + (weight * 2.50);
        System.out.printf("[SHIPPING] Shipping cost: $%.2f%n", cost);
        return cost;
    }
    
    /**
     * Schedules a shipment.
     *
     * @param orderId the order identifier
     * @param address the shipping address
     * @return tracking number if successful
     */
    public String scheduleShipment(String orderId, String address) {
        System.out.printf("[SHIPPING] Scheduling shipment for order: %s%n", orderId);
        System.out.printf("[SHIPPING] Destination: %s%n", address);
        String trackingNumber = "TRK-" + System.currentTimeMillis();
        System.out.printf("[SHIPPING] Tracking number: %s%n", trackingNumber);
        return trackingNumber;
    }
    
    /**
     * Sends shipping notification to customer.
     *
     * @param email the customer email
     * @param trackingNumber the tracking number
     */
    public void sendShippingNotification(String email, String trackingNumber) {
        System.out.printf("[SHIPPING] Sending notification to: %s%n", email);
        System.out.printf("[SHIPPING] Tracking info: %s%n", trackingNumber);
        System.out.println("[SHIPPING] Notification sent successfully");
    }
}
