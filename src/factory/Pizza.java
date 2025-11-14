package factory;

import model.Meal;

public class Pizza implements Meal {
    @Override
    public String getName() {
        return "Pizza";
    }

    @Override
    public double getPrice() {
        return 10.0;
    }

    @Override
    public String getDescription() {
        return "Delicious cheese pizza";
    }
}