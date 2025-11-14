package factory.food;

import model.Meal;

public class Burger implements Meal {
    @Override
    public String getName() {
        return "Burger";
    }

    @Override
    public double getPrice() {
        return 8.0;
    }

    @Override
    public String getDescription() {
        return "Classic beef burger";
    }
}