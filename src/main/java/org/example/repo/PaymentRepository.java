package org.example.repo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.model.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

public class PaymentRepository {

    private final MongoCollection<Document> collection;

    public PaymentRepository(MongoCollection<Document> collection) {
        this.collection = Objects.requireNonNull(collection);
    }

    /**
     * Insert a new payment into the database
     */
    public void insertPayment(Payment p) {
        Document doc = new Document()
                .append("paymentId", p.getPaymentId())
                .append("memberId", p.getMemberId())
                .append("invoiceId", p.getInvoiceId())
                .append("amount", p.getAmount())
                .append("currency", p.getCurrency())
                .append("method", p.getMethod())
                .append("status", p.getStatus())
                .append("referenceNumber", p.getReferenceNumber())
                .append("provider", p.getProvider())
                .append("date", p.getDateIso());

        collection.insertOne(doc);
    }

    /**
     * Find all payments by member name, sorted by date (newest first)
     */
    public List<Payment> findByMembername(String membername) {
        List<Payment> payments = new ArrayList<>();
        
        try (MongoCursor<Document> cursor = collection
                .find(eq("username", membername))
                .sort(descending("date"))
                .iterator()) {
            
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Payment payment = documentToPayment(doc);
                payments.add(payment);
            }
        }
        
        return payments;
    }

    /**
     * Find a payment by payment ID
     */
    public Payment findByPaymentId(String paymentId) {
        Document doc = collection.find(eq("paymentId", paymentId)).first();
        return doc != null ? documentToPayment(doc) : null;
    }

    /**
     * Count total payments for a member
     */
    public long countByMembername(String membername) {
        return collection.countDocuments(eq("memberId", membername));
    }

    /**
     * Convert MongoDB Document to Payment object
     */
    private Payment documentToPayment(Document doc) {
        Payment payment = new Payment();
        payment.setPaymentId(doc.getString("paymentId"));
        payment.setMemberId(doc.getString("memberId"));
        payment.setInvoiceId(doc.getString("invoiceId"));
        payment.setAmount(doc.getDouble("amount"));
        payment.setCurrency(doc.getString("currency"));
        payment.setMethod(doc.getString("method"));
        payment.setStatus(doc.getString("status"));
        payment.setReferenceNumber(doc.getString("referenceNumber"));
        payment.setProvider(doc.getString("provider"));
        payment.setDateIso(doc.getString("date"));
        return payment;
    }
}
