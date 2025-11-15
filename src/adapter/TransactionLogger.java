package adapter;

public class TransactionLogger {
    public static void logTransaction(String transactionId, double amount, String currency) {
        System.out.println("Transaction completed:");
        System.out.println("ID: " + transactionId);
        System.out.println("Amount: " + amount + " " + currency);
    }

    public static void logPaymentSuccess(String transactionId) {
        System.out.println("PAYMENT SUCCESSFUL");
        System.out.println("Transaction ID: " + transactionId);
    }
}