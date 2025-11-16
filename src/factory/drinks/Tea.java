package factory.drinks;

import model.Meal;

public class Tea implements Meal {
    private String type;

    public Tea(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return this.type + " Tea";
    }

    @Override
    public double getPrice() {
        switch (this.type.toLowerCase()) {
            case "moroccan":
                return 4.0;
            case "ginger":
                return 3.5;
            case "green":
                return 3.0;
            default:
                return 3.0;
        }
    }

    @Override
    public String getDescription() {
        switch (this.type.toLowerCase()) {
            case "moroccan":
                return "Refreshing Moroccan tea with mint";
            case "ginger":
                return "Spicy ginger tea with lemon";
            case "green":
                return "Healthy green tea";
            default:
                return "Tea";
        }
    }
}