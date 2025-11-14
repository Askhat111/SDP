package strategy;

public class DiscountPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.9;
    }

    @Override
    public String getStrategyName() {
        return "10% Student Discount";
    }
}