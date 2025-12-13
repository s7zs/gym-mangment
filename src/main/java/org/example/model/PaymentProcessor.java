package org.example.model;

public class PaymentProcessor {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public Payment execute(Payment payment) {
        if (strategy == null) {
            throw new IllegalStateException("PaymentStrategy not set");
        }
        return strategy.process(payment);
    }
}
