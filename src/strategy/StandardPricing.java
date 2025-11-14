package strategy;

public class StandardPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }

    @Override
    public String getStrategyName() {
        return "Standard Pricing";
    }
}