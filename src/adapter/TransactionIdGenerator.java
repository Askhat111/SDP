package adapter;

public class TransactionIdGenerator {
    public static String generate() {
        return "trs_" + System.currentTimeMillis();
    }
}