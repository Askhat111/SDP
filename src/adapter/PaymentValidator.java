package adapter;

public class PaymentValidator {
    public static boolean validateCurrency(String currency) {
        return Currency.isSupported(currency);
    }

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }
}