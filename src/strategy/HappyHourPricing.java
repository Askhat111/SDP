package strategy;

import model.Meal;
import java.time.LocalTime;

public class HappyHourPricing implements PricingStrategy {
    private static final LocalTime START_HOUR = LocalTime.of(19, 0);
    private static final LocalTime END_HOUR = LocalTime.of(20, 0);

    @Override
    public double calculatePrice(Meal meal) {
        LocalTime currentTime = LocalTime.now();


        if (currentTime.isAfter(START_HOUR) && currentTime.isBefore(END_HOUR)) {
            return meal.getPrice() * 0.5;
        } else {
            System.out.println("\nHappy Hour is only available from 19:00 to 20:00. You will be charged the standard price.");
            return meal.getPrice();
        }
    }

    @Override
    public String getStrategyName() {
        return "Happy Hour Pricing";
    }
}