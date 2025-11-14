package factory.drinks;

import model.Meal;

public class SoftDrink implements Meal {
    private String type;

    public SoftDrink(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return this.type + " Soft Drink";
    }

    @Override
    public double getPrice() {
        switch (this.type.toLowerCase()) {
            case "cola":
                return 2.5;
            case "sprite":
                return 2.5;
            case "fanta":
                return 2.5;
            default:
                return 2.0;
        }
    }

    @Override
    public String getDescription() {
        switch (this.type.toLowerCase()) {
            case "cola":
                return "Classic Coca-Cola soft drink";
            case "sprite":
                return "Refreshing Sprite with lemon flavor";
            case "fanta":
                return "Fizzy Fanta orange drink";
            default:
                return "Soft drink";
        }
    }
}