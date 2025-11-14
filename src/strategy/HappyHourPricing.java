package strategy;

public class HappyHourPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.8;
    }

    @Override
    public String getStrategyName() {
        return "Happy Hour (20% off)";
    }
}