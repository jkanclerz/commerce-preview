package pl.jkan.przelewy24;

public class RegisterTransactionData {
    private String paymentId;
    private Integer amount;

    public RegisterTransactionData(String paymentId, Integer amount) {
        this.paymentId = paymentId;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Integer getAmount() {
        return amount;
    }
}
