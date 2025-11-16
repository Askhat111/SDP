package factory.food;

import model.Meal;

public class Shashlik implements Meal {
    @Override
    public String getName() {
        return "Shashlik (Lamb)";
    }

    @Override
    public double getPrice() {
        return 16.0;
    }

    @Override
    public String getDescription() {
        return "Juicy lamb kebab grilled to perfection";
    }
}