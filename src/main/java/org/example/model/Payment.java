package org.example.model;

public class Payment {
    private String paymentId;
    private String memberId;
    private String invoiceId;
    private double amount;
    private String currency;
    private String method;
    private String status;
    private String referenceNumber;
    private String provider;
    private String dateIso;

    public Payment() {}

    public Payment(String paymentId, String memberId, String invoiceId, double amount,
                   String currency, String method, String status,
                   String referenceNumber, String provider, String dateIso) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.currency = currency;
        this.method = method;
        this.status = status;
        this.referenceNumber = referenceNumber;
        this.provider = provider;
        this.dateIso = dateIso;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getDateIso() { return dateIso; }
    public void setDateIso(String dateIso) { this.dateIso = dateIso; }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                ", provider='" + provider + '\'' +
                ", dateIso='" + dateIso + '\'' +
                '}';
    }
}
