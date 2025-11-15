package factory.drinks;

import model.Meal;

public class Coffee implements Meal {
    private String type;

    public Coffee(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return this.type + " Coffee";
    }

    @Override
    public double getPrice() {
        switch (this.type.toLowerCase()) {
            case "americano":
                return 5.0;
            case "latte":
                return 5.5;
            case "cappuccino":
                return 5.5;
            default:
                return 5.0;
        }
    }

    @Override
    public String getDescription() {
        switch (this.type.toLowerCase()) {
            case "americano":
                return "Classic Americano coffee";
            case "latte":
                return "Smooth Latte with milk";
            case "cappuccino":
                return "Foamy Cappuccino with espresso";
            default:
                return "Coffee";
        }
    }
}