package factory.food;

import model.Meal;

public class Chicken implements Meal {
    @Override
    public String getName() {
        return "Chicken";
    }

    @Override
    public double getPrice() {
        return 12.0;
    }

    @Override
    public String getDescription() {
        return "Grilled chicken served with your choice of sides";
    }
}