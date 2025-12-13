package services.dto;

/**
 * Data Transfer Object for payment processing
 */
public class PaymentDTO {
    private String membername;
    private double amount;
    private String currency;
    private String paymentMethod; // "card", "wallet", "cash", "online"
    private String invoiceId;     // optional
    
    // Optional fields for specific payment methods
    private String provider;       // for online payments
    private String referenceNumber; // for wallet payments

    public PaymentDTO() {
    }

    public PaymentDTO(String membername, double amount, String currency, String paymentMethod) {
        this.membername = membername;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

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

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "membername='" + membername + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", provider='" + provider + '\'' +
                ", referenceNumber='" + referenceNumber + '\'' +
                '}';
    }
}
