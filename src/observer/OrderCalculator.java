package observer;

import model.Meal;
import java.util.List;

public class OrderCalculator {
    public static double calculateSubtotal(List<Meal> meals) {
        return meals.stream().mapToDouble(Meal::getPrice).sum();
    }

    public static int getItemCount(List<Meal> meals) {
        return meals.size();
    }
}