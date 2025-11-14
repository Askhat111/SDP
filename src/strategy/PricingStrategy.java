package strategy;

import model.Meal;

public interface PricingStrategy {
    double calculatePrice(Meal meal);
    String getStrategyName();
}