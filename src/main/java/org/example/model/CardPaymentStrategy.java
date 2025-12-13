package org.example.model;

public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment process(Payment payment) {
        payment.setStatus("SUCCESS");
        payment.setReferenceNumber("CARD-" + shortRef());
        payment.setProvider("Card");
        return payment;
    }

    private String shortRef() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase().substring(0, 8);
    }
}
