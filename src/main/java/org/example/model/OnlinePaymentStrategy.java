package org.example.model;

public class OnlinePaymentStrategy implements PaymentStrategy {

    @Override
    public Payment process(Payment payment) {
        boolean ok = payment.getProvider() != null && !payment.getProvider().isEmpty();
        payment.setStatus(ok ? "SUCCESS" : "FAILED");
        payment.setReferenceNumber(ok ? "ONLINE-" + shortRef() : "ONLINE-FAIL");
        return payment;
    }

    private String shortRef() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase().substring(0, 8);
    }
}
