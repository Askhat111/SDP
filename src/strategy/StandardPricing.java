package strategy;

import model.Meal;

public class StandardPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Meal meal) {
        return meal.getPrice();
    }

    @Override
    public String getStrategyName() {
        return "Standard Pricing";
    }
}