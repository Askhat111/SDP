package adapter;

public class PaymentAdapter implements PaymentProcessor {
    private final ExternalPaymentAPI externalAPI;

    public PaymentAdapter(ExternalPaymentAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public boolean processPayment(double amount, String orderId, String currency) {
        if (!isValidPayment(amount, currency)) {
            return false;
        }

        ExternalPaymentAPI.PaymentRequest request =
                new ExternalPaymentAPI.PaymentRequest(amount, currency, "restaurant");
        return externalAPI.processPayment(request).isSuccess();
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

    private boolean isValidPayment(double amount, String currency) {
        return amount > 0 && Currency.isSupported(currency);
    }
}