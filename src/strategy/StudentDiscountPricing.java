package strategy;

import model.Meal;

public class StudentDiscountPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Meal meal) {
        return meal.getPrice() * 0.9;
    }

    @Override
    public String getStrategyName() {
        return "Student Discount Pricing";
    }
}