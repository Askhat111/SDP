package adapter;

public class PaymentAdapter implements PaymentProcessor {
    private final ExternalPaymentAPI externalAPI;

    public PaymentAdapter(ExternalPaymentAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public boolean processPayment(double amount, String orderId, String currency) {
        System.out.println("PROCESSING PAYMENT");
        System.out.println("Order: " + orderId);
        System.out.println("Amount: " + getCurrencySymbol(currency) + amount + " " + currency);

        if (!isValidPayment(amount, currency)) {
            return false;
        }

        ExternalPaymentAPI.PaymentRequest request = createPaymentRequest(amount, currency);
        PaymentResult result = externalAPI.processPayment(request);

        logPaymentResult(result);
        return result.isSuccess();
    }

    @Override
    public boolean supportsCurrency(String currency) {
        return externalAPI.isCurrencySupported(currency);
    }

    @Override
    public String getSupportedCurrencies() {
        return externalAPI.getSupportedCurrencies();
    }

    @Override
    public String getProcessorName() {
        return "Payment Gateway Adapter";
    }

    private boolean isValidPayment(double amount, String currency) {
        if (!PaymentValidator.validateCurrency(currency)) {
            System.out.println("Unsupported currency: " + currency);
            System.out.println("Supported: " + getSupportedCurrencies());
            return false;
        }

        if (!PaymentValidator.validateAmount(amount)) {
            System.out.println("Invalid amount: " + amount);
            return false;
        }

        return true;
    }

    private ExternalPaymentAPI.PaymentRequest createPaymentRequest(double amount, String currency) {
        return new ExternalPaymentAPI.PaymentRequest(amount, currency, "restaurant");
    }

    private String getCurrencySymbol(String currency) {
        try {
            return Currency.valueOf(currency.toUpperCase()).getSymbol();
        } catch (IllegalArgumentException e) {
            return "";
        }
    }

    private void logPaymentResult(PaymentResult result) {
        TransactionLogger.logPaymentSuccess(result.getTransactionId());
    }
}