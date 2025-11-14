package restaurant.factory;

import restaurant.model.Meal;

public class MealFactory {
    public Meal createMeal(MealType type) {
        switch (type) {
            case BURGER:
                return new Burger();
            case PIZZA:
                return new Pizza();
            case SALAD:
                return new Salad();
            default:
                throw new IllegalArgumentException("Unknown meal type: " + type);
        }
    }
}