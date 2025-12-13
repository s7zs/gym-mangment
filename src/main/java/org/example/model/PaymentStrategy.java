package org.example.model;

public interface PaymentStrategy {
    Payment process(Payment payment);
}
