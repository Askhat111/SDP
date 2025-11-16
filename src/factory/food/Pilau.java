package factory.food;

import model.Meal;

public class Pilau implements Meal {
    @Override
    public String getName() {
        return "Pilau";
    }

    @Override
    public double getPrice() {
        return 15.0;
    }

    @Override
    public String getDescription() {
        return "Traditional rice dish with meat, carrots, and spices";
    }
}