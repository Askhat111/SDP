package strategy;

public interface PricingStrategy {
    double calculatePrice(double basePrice);
    String getStrategyName();
}