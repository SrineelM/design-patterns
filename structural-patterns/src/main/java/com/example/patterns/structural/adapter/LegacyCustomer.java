package com.example.patterns.structural.adapter;

/**
 * Legacy customer class from an old system.
 * 
 * <p>This represents the Adaptee in the Adapter pattern.
 * This class has an incompatible interface with the new system
 * but contains the same essential data in a different format.
 * 
 * @author Design Patterns Team
 * @version 1.0.0
 */
public class LegacyCustomer {
    
    private final int customerNumber;
    private final String firstName;
    private final String lastName;
    private final String emailAddr;
    private final String telephone;
    private final String street;
    private final String city;
    private final String zipCode;
    
    /**
     * Creates a new legacy customer record.
     *
     * @param customerNumber the numeric customer identifier
     * @param firstName the first name
     * @param lastName the last name
     * @param emailAddr the email address
     * @param telephone the phone number
     * @param street the street address
     * @param city the city name
     * @param zipCode the postal code
     */
    public LegacyCustomer(int customerNumber, String firstName, String lastName, 
                         String emailAddr, String telephone, String street, 
                         String city, String zipCode) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
        this.telephone = telephone;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    public int getCustomerNumber() {
        return customerNumber;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmailAddr() {
        return emailAddr;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getZipCode() {
        return zipCode;
    }
}
