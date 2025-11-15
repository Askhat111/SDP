package adapter;

public interface PaymentProcessor {
    boolean processPayment(double amount, String orderId, String currency);
    boolean supportsCurrency(String currency);
    String getSupportedCurrencies();
    String getProcessorName();
}