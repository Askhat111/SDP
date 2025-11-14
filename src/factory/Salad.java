package factory;

import model.Meal;

public class Salad implements Meal {
    @Override
    public String getName() {

        return "Salad";
    }

    @Override
    public double getPrice() {

        return 1500.0;
    }
}