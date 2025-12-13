package org.example.model;

public class WalletPaymentStrategy implements PaymentStrategy {

    @Override
    public Payment process(Payment payment) {
        boolean ok = payment.getReferenceNumber() != null && !payment.getReferenceNumber().isEmpty();
        payment.setStatus(ok ? "SUCCESS" : "FAILED");
        payment.setReferenceNumber(ok ? "WALLET-" + shortRef() : "WALLET-FAIL");
        payment.setProvider("Wallet");
        return payment;
    }

    private String shortRef() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase().substring(0, 8);
    }
}
