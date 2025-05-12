package org.example.proyectofinalnat20.services;

public interface PaymentService {
    // Define the methods that will be implemented in the PaymentServiceImpl class
    void processPayment(String paymentDetails);
    boolean validatePayment(String paymentDetails);
    void refundPayment(String paymentId);
    String getPaymentStatus(String paymentId);
}
