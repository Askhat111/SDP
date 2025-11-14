package adapter;

public class PaymentAdapter implements PaymentProcessor {
    private ExternalPaymentAPI externalAPI;

    public PaymentAdapter(ExternalPaymentAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    public boolean processPayment(double dollars, String orderId) {
        int cents = (int)(dollars * 100);
        externalAPI.makePayment(cents, "RESTAURANT_" + orderId);
        return externalAPI.isSuccessful();
    }
}