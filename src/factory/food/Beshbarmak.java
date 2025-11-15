package factory.food;

import model.Meal;

public class Beshbarmak implements Meal {
    @Override
    public String getName() {
        return "Beshbarmak";
    }

    @Override
    public double getPrice() {
        return 14.0;
    }

    @Override
    public String getDescription() {
        return "Traditional Kazakh dish with noodles, meat, and onions";
    }
}