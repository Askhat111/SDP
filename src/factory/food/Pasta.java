package factory.food;

import model.Meal;

public class Pasta implements Meal {
    @Override
    public String getName() {
        return "Pasta";
    }

    @Override
    public double getPrice() {
        return 12.0;
    }

    @Override
    public String getDescription() {
        return "Creamy pasta with tomato sauce";
    }
}