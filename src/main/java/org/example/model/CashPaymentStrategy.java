package org.example.model;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment process(Payment payment) {
        payment.setStatus("SUCCESS");
        payment.setReferenceNumber("CASH-" + shortRef());
        payment.setProvider("Cash");
        return payment;
    }

    private String shortRef() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase().substring(0, 8);
    }
}
