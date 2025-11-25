package com.example.patterns.creational.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Configuration for Payment Factory pattern.
 * 
 * This configuration class demonstrates how to integrate the Factory Method pattern
 * with Spring Framework. It provides Spring Beans for the payment-related components,
 * allowing them to be injected into other Spring components.
 * 
 * <h3>Spring Integration Benefits:</h3>
 * <ul>
 *     <li>Dependency injection support</li>
 *     <li>Configuration management</li>
 *     <li>Bean lifecycle management</li>
 *     <li>AOP and proxy support</li>
 * </ul>
 * 
 * <h3>Usage in Spring Applications:</h3>
 * <pre>
 * @SpringBootApplication
 * public class Application {
 *     public static void main(String[] args) {
 *         SpringApplication.run(Application.class, args);
 *     }
 * }
 * 
 * @RestController
 * public class PaymentController {
 *     @Autowired
 *     private PaymentService paymentService;
 *     
 *     @PostMapping("/api/payment")
 *     public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
 *         return paymentService.processPayment(request);
 *     }
 * }
 * 
 * @Service
 * public class PaymentService {
 *     public PaymentResult processPayment(PaymentRequest request) {
 *         // Factory method still applies - Spring doesn't change the pattern
 *         Payment payment = PaymentFactory.createPayment(request.getPaymentMethod());
 *         return payment.process(request.getAmount(), request.getOrderId());
 *     }
 * }
 * </pre>
 * 
 * <h3>Configuration Example (application.properties):</h3>
 * <pre>
 * # Payment Configuration
 * payment.creditcard.processor=stripe
 * payment.creditcard.max-amount=10000
 * payment.paypal.api-endpoint=https://api.paypal.com
 * payment.google-pay.processor=google-gateway
 * </pre>
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 * @since Java 17
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Bean
 */
@Configuration
public class PaymentFactoryConfiguration {
    
    /**
     * Provides a Spring Bean for the PaymentFactory.
     * 
     * Although PaymentFactory uses static methods, this bean registration
     * demonstrates how to make the factory available to Spring's dependency injection.
     * 
     * <h3>Benefits of Bean Registration:</h3>
     * <ul>
     *     <li>Spring manages the factory lifecycle</li>
     *     <li>Can be injected into other components if wrapped in a service</li>
     *     <li>Enables AOP decorators and proxies</li>
     *     <li>Allows testing with mocks</li>
     * </ul>
     * 
     * @return PaymentFactory instance for Spring dependency injection
     * 
     * Example (if wrapped in a service):
     * <pre>
     * @Service
     * public class PaymentCreationService {
     *     public Payment createPayment(PaymentMethod method) {
     *         return PaymentFactory.createPayment(method);
     *     }
     * }
     * 
     * @Autowired
     * private PaymentCreationService paymentService;
     * </pre>
     */
    @Bean
    public PaymentFactoryWrapper paymentFactory() {
        return new PaymentFactoryWrapper();
    }
    
    /**
     * Provides Spring Beans for CreditCardPayment.
     * 
     * This demonstrates how to register concrete payment implementations as beans,
     * though typically the factory method creates them.
     * 
     * @return a new CreditCardPayment instance managed by Spring
     */
    @Bean
    public CreditCardPayment creditCardPayment() {
        return new CreditCardPayment();
    }
    
    /**
     * Provides Spring Beans for PayPalPayment.
     * 
     * @return a new PayPalPayment instance managed by Spring
     */
    @Bean
    public PayPalPayment paypalPayment() {
        return new PayPalPayment();
    }
    
    /**
     * Provides Spring Beans for GooglePayPayment.
     * 
     * @return a new GooglePayPayment instance managed by Spring
     */
    @Bean
    public GooglePayPayment googlePayPayment() {
        return new GooglePayPayment();
    }
    
    /**
     * Provides Spring Bean for OrderProcessor.
     * 
     * @return a new OrderProcessor instance managed by Spring
     */
    @Bean
    public OrderProcessor orderProcessor() {
        return new OrderProcessor();
    }
    
    /**
     * Wrapper class for PaymentFactory to enable Spring integration.
     * 
     * This wrapper allows PaymentFactory's static methods to be used within
     * Spring's dependency injection framework.
     */
    public static class PaymentFactoryWrapper {
        
        /**
         * Creates a payment using the factory method.
         * 
         * This method wraps the static factory method for Spring injection compatibility.
         * 
         * @param method the payment method to create
         * @return the created Payment instance
         */
        public Payment createPayment(PaymentMethod method) {
            return PaymentFactory.createPayment(method);
        }
        
        /**
         * Creates a payment using the factory method by name.
         * 
         * @param methodName the payment method name
         * @return the created Payment instance
         */
        public Payment createPaymentByName(String methodName) {
            return PaymentFactory.createPaymentByName(methodName);
        }
    }
}
