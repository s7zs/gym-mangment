package services;

import org.example.model.Payment;
import org.example.repo.PaymentRepository;

public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public void createPayment(Payment payment) {
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            throw new IllegalArgumentException("paymentId is required");
        }
        repo.insertPayment(payment);
    }
}
