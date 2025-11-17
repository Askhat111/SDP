package adapter;

public class ExternalPaymentAPI {
    public PaymentResult processPayment(PaymentRequest request) {
        displayPaymentInfo(request);

        String transactionId = "trs_" + (10000 + (int)(Math.random() * 90000));
        logTransaction(transactionId, request.getAmount(), request.getCurrency());
        return new PaymentResult(true, transactionId, "Payment completed successfully");
    }

    private void displayPaymentInfo(PaymentRequest request) {
        System.out.println("PROCESSING PAYMENT");
        System.out.println("Seller: " + request.getMerchantId());
        System.out.println("Amount: " + request.getAmount() + " " + request.getCurrency());
        System.out.println("Processing payment...");
    }

    private void logTransaction(String transactionId, double amount, String currency) {
        System.out.println("Transaction completed:");
        System.out.println("ID: " + transactionId);
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("PAYMENT SUCCESSFUL");
        System.out.println("Transaction ID: " + transactionId);
    }

    public boolean isCurrencySupported(String currency) {
        return Currency.isSupported(currency);
    }

    public String getSupportedCurrencies() {
        return "USD, EUR, KZT, GBP";
    }

    public static class PaymentRequest {
        private final double amount;
        private final String currency;
        private final String merchantId;

        public PaymentRequest(double amount, String currency, String merchantId) {
            this.amount = amount;
            this.currency = currency;
            this.merchantId = merchantId;
        }

        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getMerchantId() { return merchantId; }
    }
}