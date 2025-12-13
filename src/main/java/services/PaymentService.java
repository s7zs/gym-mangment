package services;

import org.example.model.*;
import org.example.repo.PaymentRepository;
import services.dto.PaymentDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class PaymentService {

    private final PaymentRepository repo;
    private final PaymentProcessor processor;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
        this.processor = new PaymentProcessor();
    }

    /**
     * Process a payment using Strategy Pattern based on payment method
     * Auto-generates payment ID and timestamp
     * 
     * @param paymentData Payment data from user
     * @return Processed Payment object
     */
    public Payment processPayment(PaymentDTO paymentData) {
        // Validate input
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }
        if (paymentData.getMembername() == null || paymentData.getMembername().trim().isEmpty()) {
            throw new IllegalArgumentException("Member name is required");
        }
        if (paymentData.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (paymentData.getPaymentMethod() == null || paymentData.getPaymentMethod().trim().isEmpty()) {
            throw new IllegalArgumentException("Payment method is required");
        }

        // Create Payment object
        Payment payment = new Payment();
        payment.setPaymentId(generatePaymentId());
        payment.setMemberId(paymentData.getMembername());
        payment.setInvoiceId(paymentData.getInvoiceId());
        payment.setAmount(paymentData.getAmount());
        payment.setCurrency(paymentData.getCurrency() != null ? paymentData.getCurrency() : "USD");
        payment.setMethod(paymentData.getPaymentMethod());
        payment.setDateIso(generateTimestamp());
        
        // Set optional fields for specific payment methods
        if (paymentData.getProvider() != null) {
            payment.setProvider(paymentData.getProvider());
        }
        if (paymentData.getReferenceNumber() != null) {
            payment.setReferenceNumber(paymentData.getReferenceNumber());
        }

        // Select appropriate payment strategy based on payment method
        PaymentStrategy strategy = selectStrategy(paymentData.getPaymentMethod());
        processor.setStrategy(strategy);

        // Process payment using selected strategy
        Payment processedPayment = processor.execute(payment);

        // Save to database
        repo.insertPayment(processedPayment);

        return processedPayment;
    }

    /**
     * Get payment history for a specific member
     */
    public List<Payment> getPaymentHistory(String membername) {
        if (membername == null || membername.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name is required");
        }
        return repo.findByMembername(membername);
    }

    /**
     * Get a specific payment by ID
     */
    public Payment getPaymentById(String paymentId) {
        return repo.findByPaymentId(paymentId);
    }

    /**
     * Get total payment count for a member
     */
    public long getPaymentCount(String membername) {
        return repo.countByMembername(membername);
    }

    /**
     * Select payment strategy based on payment method
     * Strategy Pattern implementation
     */
    private PaymentStrategy selectStrategy(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "card":
                return new CardPaymentStrategy();
            case "wallet":
                return new WalletPaymentStrategy();
            case "cash":
                return new CashPaymentStrategy();
            case "online":
                return new OnlinePaymentStrategy();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethod + 
                    ". Supported methods: card, wallet, cash, online");
        }
    }

    /**
     * Generate unique payment ID
     */
    private String generatePaymentId() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Generate ISO timestamp
     */
    private String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Legacy method for backward compatibility
     */
    @Deprecated
    public void createPayment(Payment payment) {
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            throw new IllegalArgumentException("paymentId is required");
        }
        repo.insertPayment(payment);
    }
}
