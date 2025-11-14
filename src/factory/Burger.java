package factory;

import model.Meal;

public class Burger implements Meal {
    @Override
    public String getName() {

        return "Burger";
    }

    @Override
    public double getPrice() {

        return 2000.0;
    }
}
