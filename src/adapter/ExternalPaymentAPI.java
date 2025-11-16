package adapter;

public class ExternalPaymentAPI {
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("Processing payment through external gateway...");
        System.out.println("Merchant: " + request.getMerchantId());
        System.out.println("Amount: " + request.getAmount() + " " + request.getCurrency());

        String transactionId = "trs_" + System.currentTimeMillis();
        logTransaction(transactionId, request.getAmount(), request.getCurrency());

        return new PaymentResult(true, transactionId, "Payment completed successfully");
    }

    private void logTransaction(String transactionId, double amount, String currency) {
        System.out.println("Transaction completed:");
        System.out.println("ID: " + transactionId);
        System.out.println("Amount: " + amount + " " + currency);
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