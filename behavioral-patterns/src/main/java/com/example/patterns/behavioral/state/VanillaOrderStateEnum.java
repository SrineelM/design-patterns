package com.example.patterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum-based implementation of order states (Vanilla Implementation).
 * 
 * <p>Using enums for states is a Java idiom that combines the State pattern
 * with the type safety and singleton nature of enums.</p>
 * 
 * <h3>State Transitions:</h3>
 * <pre>
 * NEW → pay() → PAID → ship() → SHIPPED → deliver() → DELIVERED
 *  ↓             ↓         ↓
 * cancel()    cancel()  cancel()
 *  ↓             ↓         ↓
 * CANCELLED   CANCELLED  CANCELLED
 * </pre>
 * 
 * @since 1.0
 */
public enum VanillaOrderStateEnum implements VanillaOrderState {
    
    /**
     * Initial state when order is created.
     */
    NEW {
        private static final Logger log = LoggerFactory.getLogger("OrderState.NEW");
        
        @Override
        public void pay(VanillaOrderContext context) {
            log.info("Processing payment for order #{}", context.getOrderId());
            context.setState(PAID);
            log.info("Order #{} is now PAID", context.getOrderId());
        }
        
        @Override
        public void ship(VanillaOrderContext context) {
            log.warn("Cannot ship order #{} - payment required first", context.getOrderId());
        }
        
        @Override
        public void deliver(VanillaOrderContext context) {
            log.warn("Cannot deliver order #{} - not yet shipped", context.getOrderId());
        }
        
        @Override
        public void cancel(VanillaOrderContext context) {
            log.info("Cancelling new order #{}", context.getOrderId());
            context.setState(CANCELLED);
        }
        
        @Override
        public String getStateName() {
            return "NEW";
        }
    },
    
    /**
     * State after payment is received.
     */
    PAID {
        private static final Logger log = LoggerFactory.getLogger("OrderState.PAID");
        
        @Override
        public void pay(VanillaOrderContext context) {
            log.warn("Order #{} is already paid", context.getOrderId());
        }
        
        @Override
        public void ship(VanillaOrderContext context) {
            log.info("Shipping order #{}...", context.getOrderId());
            log.info("Preparing package, printing label, notifying courier...");
            context.setState(SHIPPED);
            log.info("Order #{} is now SHIPPED", context.getOrderId());
        }
        
        @Override
        public void deliver(VanillaOrderContext context) {
            log.warn("Cannot deliver order #{} - not yet shipped", context.getOrderId());
        }
        
        @Override
        public void cancel(VanillaOrderContext context) {
            log.info("Cancelling paid order #{} - processing refund", context.getOrderId());
            context.setState(CANCELLED);
        }
        
        @Override
        public String getStateName() {
            return "PAID";
        }
    },
    
    /**
     * State when order is in transit.
     */
    SHIPPED {
        private static final Logger log = LoggerFactory.getLogger("OrderState.SHIPPED");
        
        @Override
        public void pay(VanillaOrderContext context) {
            log.warn("Order #{} is already paid and shipped", context.getOrderId());
        }
        
        @Override
        public void ship(VanillaOrderContext context) {
            log.warn("Order #{} is already shipped", context.getOrderId());
        }
        
        @Override
        public void deliver(VanillaOrderContext context) {
            log.info("Delivering order #{}...", context.getOrderId());
            log.info("Customer signature received, order completed");
            context.setState(DELIVERED);
            log.info("Order #{} is now DELIVERED", context.getOrderId());
        }
        
        @Override
        public void cancel(VanillaOrderContext context) {
            log.warn("Cannot cancel order #{} - already shipped. Contact support for return.", 
                     context.getOrderId());
        }
        
        @Override
        public String getStateName() {
            return "SHIPPED";
        }
    },
    
    /**
     * Final state when order is delivered.
     */
    DELIVERED {
        private static final Logger log = LoggerFactory.getLogger("OrderState.DELIVERED");
        
        @Override
        public void pay(VanillaOrderContext context) {
            log.warn("Order #{} is already completed", context.getOrderId());
        }
        
        @Override
        public void ship(VanillaOrderContext context) {
            log.warn("Order #{} is already delivered", context.getOrderId());
        }
        
        @Override
        public void deliver(VanillaOrderContext context) {
            log.warn("Order #{} is already delivered", context.getOrderId());
        }
        
        @Override
        public void cancel(VanillaOrderContext context) {
            log.warn("Cannot cancel order #{} - already delivered. Request return instead.", 
                     context.getOrderId());
        }
        
        @Override
        public String getStateName() {
            return "DELIVERED";
        }
    },
    
    /**
     * Terminal state when order is cancelled.
     */
    CANCELLED {
        private static final Logger log = LoggerFactory.getLogger("OrderState.CANCELLED");
        
        @Override
        public void pay(VanillaOrderContext context) {
            log.warn("Cannot pay for cancelled order #{}", context.getOrderId());
        }
        
        @Override
        public void ship(VanillaOrderContext context) {
            log.warn("Cannot ship cancelled order #{}", context.getOrderId());
        }
        
        @Override
        public void deliver(VanillaOrderContext context) {
            log.warn("Cannot deliver cancelled order #{}", context.getOrderId());
        }
        
        @Override
        public void cancel(VanillaOrderContext context) {
            log.warn("Order #{} is already cancelled", context.getOrderId());
        }
        
        @Override
        public String getStateName() {
            return "CANCELLED";
        }
    }
}
