package services;

import org.example.model.Payment;
import org.example.model.users;
import org.example.repo.Userrepo;
import services.dto.PaymentDTO;

import java.util.List;

/**
 * User services for member-specific operations
 * Includes payment processing and payment history
 */
public class userservices {
    
    private final Userrepo userRepo;
    private final PaymentService paymentService;

    public userservices(Userrepo userRepo, PaymentService paymentService) {
        this.userRepo = userRepo;
        this.paymentService = paymentService;
    }

    /**
     * Make a payment for a member using Strategy Pattern
     * Validates member existence before processing payment
     * 
     * @param membername Member's username
     * @param paymentData Payment details (amount, currency, payment method)
     * @return Processed Payment object with payment ID, status, and reference number
     * @throws IllegalArgumentException if member doesn't exist or payment data is invalid
     */
    public Payment makePayment(String membername, PaymentDTO paymentData) {
        // Validate member exists using userRepo.getProfile
        users member = userRepo.findByUsername(membername);
        
        if (member == null) {
            throw new IllegalArgumentException("Member not found: " + membername);
        }

        // Ensure membername in paymentData matches
        paymentData.setMembername(membername);

        // Process payment using PaymentService (which uses Strategy Pattern)
        Payment processedPayment = paymentService.processPayment(paymentData);

        System.out.println("✓ Payment processed successfully for member: " + membername);
        System.out.println("  - Payment ID: " + processedPayment.getPaymentId());
        System.out.println("  - Amount: " + processedPayment.getCurrency() + " " + processedPayment.getAmount());
        System.out.println("  - Method: " + processedPayment.getMethod());
        System.out.println("  - Status: " + processedPayment.getStatus());
        System.out.println("  - Reference: " + processedPayment.getReferenceNumber());

        return processedPayment;
    }

    /**
     * View payment history for a specific member
     * Returns all payments sorted by date (newest first)
     * 
     * @param membername Member's username
     * @return List of Payment objects
     * @throws IllegalArgumentException if member doesn't exist
     */
    public List<Payment> viewPaymentHistory(String membername) {
        // Validate member exists
        users member = userRepo.findByUsername(membername);
        
        if (member == null) {
            throw new IllegalArgumentException("Member not found: " + membername);
        }

        // Get payment history from PaymentService
        List<Payment> paymentHistory = paymentService.getPaymentHistory(membername);

        System.out.println("Payment history for member: " + membername);
        System.out.println("Total payments: " + paymentHistory.size());

        if (!paymentHistory.isEmpty()) {
            System.out.println("\nRecent payments:");
            int count = 0;
            for (Payment payment : paymentHistory) {
                if (count++ >= 5) break; // Show only last 5
                System.out.println("  - " + payment.getDateIso() + 
                    " | " + payment.getCurrency() + " " + payment.getAmount() + 
                    " | " + payment.getMethod() + 
                    " | " + payment.getStatus());
            }
        }

        return paymentHistory;
    }

    /**
     * Get total amount paid by a member
     * 
     * @param membername Member's username
     * @return Total amount paid
     */
    public double getTotalPaid(String membername) {
        // Validate member exists
        users member = userRepo.findByUsername(membername);
        if (member == null) {
            throw new IllegalArgumentException("Member not found: " + membername);
        }
        
        // Get payments without printing history
        List<Payment> payments = paymentService.getPaymentHistory(membername);
        return payments.stream()
                .filter(p -> "SUCCESS".equals(p.getStatus()))
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    /**
     * Get payment statistics for a member
     * 
     * @param membername Member's username
     * @return Payment count
     */
    public long getPaymentCount(String membername) {
        return paymentService.getPaymentCount(membername);
    }
}
