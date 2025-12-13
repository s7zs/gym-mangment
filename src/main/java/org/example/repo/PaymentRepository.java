package org.example.repo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.model.Payment;

import java.util.Objects;

public class PaymentRepository {

    private final MongoCollection<Document> collection;

    public PaymentRepository(MongoCollection<Document> collection) {
        this.collection = Objects.requireNonNull(collection);
    }

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
}
