package adapter;

public enum Currency {
    USD("$"), EUR("€"), KZT("₸"), GBP("£");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static boolean isSupported(String currency) {
        try {
            Currency.valueOf(currency.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}