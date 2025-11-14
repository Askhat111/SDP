package adapter;

public class ExternalPaymentAPI {
    public void makePayment(int cents, String merchant) {
        System.out.println("External API: Processing " + cents + " cents for " + merchant);
    }

    public boolean isSuccessful() {
        return true;
    }
}