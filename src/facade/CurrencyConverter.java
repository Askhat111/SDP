package facade;
import java.util.Map;

public class CurrencyConverter{
    private final Map<String, Double> rates;

    public CurrencyConverter(){
        this.rates = Map.of(
                "USD", 1.0,
                "KZT", 524.6,
                "EUR", 0.86,
                "GBP", 0.76
        );
    }

    public boolean isSupported(String currency){
        return rates.containsKey(currency);
    }

    public String getOrDefaultCurrency(String currency){
        if (!isSupported(currency)) {
            System.out.println("Unsupported currency. Using USD.");
            return "USD";
        }
        return currency;
    }
    public double convertFromUsd(double amountUsd, String currency){
        double rate = rates.get(currency);
        return amountUsd * rate;
    }

    public void showConversion(double amountUsd, double convertedAmount, String currency){
        double rate = rates.get(currency);
        System.out.println("Currency conversion:");
        System.out.printf("$%.2f USD = %.2f %s%n", amountUsd, convertedAmount, currency);
        System.out.printf("Exchange rate: 1 USD = %.2f %s%n", rate, currency);
    }
}
