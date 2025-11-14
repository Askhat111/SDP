package factory;

import model.Meal;

public class MealFactory {
    public Meal createMeal(MealType type) {
        switch (type) {
            case PIZZA:
                return new Pizza();
            case BURGER:
                return new Burger();
            case SALAD:
                return new Salad();
            default:
                throw new IllegalArgumentException("Unknown meal type: " + type);
        }
    }
}