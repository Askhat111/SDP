package facade;
import adapter.ExternalPaymentAPI;
import adapter.PaymentAdapter;
import adapter.PaymentProcessor;
import observer.Order;
import java.util.Scanner;

public class PaymentService{
    private final PaymentProcessor paymentProcessor;
    private final CurrencyConverter converter;
    private final Scanner scanner;

    public PaymentService(Scanner scanner) {
        this.paymentProcessor = new PaymentAdapter(new ExternalPaymentAPI());
        this.converter = new CurrencyConverter();
        this.scanner = scanner;
    }

    public boolean pay(double amountUsd, Order order) {
        System.out.print("Enter currency (USD, EUR, KZT, GBP): ");
        String currency = scanner.nextLine().trim().toUpperCase();
        currency = converter.getOrDefaultCurrency(currency);

        double convertedAmount = converter.convertFromUsd(amountUsd, currency);
        converter.showConversion(amountUsd, convertedAmount, currency);

        boolean success = paymentProcessor.processPayment(convertedAmount, order.getOrderId(), currency);

        if (success) {
            System.out.println("Payment successful via " + paymentProcessor.getProcessorName());
        } else {
            System.out.println("Payment failed. Please try again.");
        }
        return success;
    }
}
