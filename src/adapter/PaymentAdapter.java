package adapter;

import java.util.Map;

public class PaymentAdapter implements PaymentProcessor {
    private final ExternalPaymentAPI externalAPI;
    private final Map<String, Double> exchangeRates;

    public PaymentAdapter(ExternalPaymentAPI externalAPI) {
        this.externalAPI = externalAPI;
        this.exchangeRates = initializeExchangeRates();
    }

    private Map<String, Double> initializeExchangeRates() {
        return Map.of(
                "USD", 1.0,
                "KZT", 476.19,
                "EUR", 0.93,
                "GBP", 0.79
        );
    }

    @Override
    public boolean processPayment(double amountUSD, String orderId, String currency) {
        double convertedAmount = convertCurrency(amountUSD, "USD", currency);
        displayPaymentInfo(amountUSD, convertedAmount, orderId, currency);

        if (!isValidPayment(convertedAmount, currency)) {
            return false;
        }

        ExternalPaymentAPI.PaymentRequest request = createPaymentRequest(convertedAmount, currency);
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
        return "Payment Adapter";
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }

    private void displayPaymentInfo(double amountUSD, double convertedAmount, String orderId, String currency) {
        System.out.println("PROCESSING PAYMENT");
        System.out.println("Order: " + orderId);
        System.out.println("Original Amount: $" + amountUSD + " USD");
        System.out.println("Converted Amount: " + getCurrencySymbol(currency) + convertedAmount + " " + currency);
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