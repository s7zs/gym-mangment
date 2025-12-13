package org.example.model;

public class Payment {
    private String paymentId;
    private String membername;
    private String invoiceId;
    private double amount;
    private String currency;
    private String method;
    private String status;
    private String referenceNumber;
    private String provider;
    private String dateIso;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Payment() {}

    public Payment(String paymentId, String memberId, String invoiceId, double amount,
                   String currency, String method, String status, String dateIso) {
        this.paymentId = paymentId;
        this.membername = memberId;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.currency = currency;
        this.method = method;
        this.status = status;
      
        this.dateIso = dateIso;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getMemberId() { return membername; }
    public void setMemberId(String membername) { this.membername = membername; }
    
    public String getMembername() { return membername; }
    public void setMembername(String membername) { this.membername = membername; }

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



    public String getDateIso() { return dateIso; }
    public void setDateIso(String dateIso) { this.dateIso = dateIso; }

  
}
