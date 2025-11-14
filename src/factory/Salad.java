package factory;

import model.Meal;

public class Salad implements Meal {
    @Override
    public String getName() {
        return "Salad";
    }

    @Override
    public double getPrice() {
        return 6.0;
    }

    @Override
    public String getDescription() {
        return "Fresh garden salad";
    }
}