package factory.food;

import model.Meal;

public class Manti implements Meal {
    @Override
    public String getName() {
        return "Manti";
    }

    @Override
    public double getPrice() {
        return 13.0;
    }

    @Override
    public String getDescription() {
        return "Kazakh steamed dumplings stuffed with meat and onions";
    }
}