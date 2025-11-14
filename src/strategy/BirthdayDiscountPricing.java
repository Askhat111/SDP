package strategy;

import model.Meal;
import java.time.LocalDate;

public class BirthdayDiscountPricing implements PricingStrategy {

    @Override
    public double calculatePrice(Meal meal) {

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(today.getYear(), 11, 15);

        if (today.equals(birthday)) {
            System.out.println("\nHappy Birthday🎉");
            return meal.getPrice() * 0.8;
        }
        else{
            System.out.println("\nToday is not birthday");
            return meal.getPrice();
        }
    }

    @Override
    public String getStrategyName() {
        return "Birthday Discount Pricing";
    }
}