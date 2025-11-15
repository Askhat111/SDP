package strategy;

import model.Meal;

public class LoyaltyDiscountPricing implements PricingStrategy {

    private int customerOrders;

    public LoyaltyDiscountPricing(int customerOrders) {
        this.customerOrders = customerOrders;
    }
    @Override
    public double calculatePrice(Meal meal) {
        if (customerOrders > 5) {
            return meal.getPrice() * 0.85;
        }
        return meal.getPrice();
    }
    @Override
    public String getStrategyName() {
        return "Loyalty Discount Pricing";
    }
}