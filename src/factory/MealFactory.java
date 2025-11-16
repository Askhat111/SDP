package factory;

import factory.food.*;
import factory.drinks.*;

public class MealFactory {

    public static model.Meal createFood(String foodType) {
        switch (foodType.toLowerCase()) {
            case "pizza":
                return new Pizza();
            case "burger":
                return new Burger();
            case "salad":
                return new Salad();
            case "beshbarmak":
                return new Beshbarmak();
            case "shashlik":
                return new Shashlik();
            case "chicken":
                return new Chicken();
            case "manti":
                return new Manti();
            case "pilau":
                return new Pilau();
            default:
                throw new IllegalArgumentException("Unknown food type: " + foodType);
        }
    }

    public static model.Meal createDrink(String drinkType) {
        switch (drinkType.toLowerCase()) {
            case "moroccan tea":
                return new Tea("moroccan");
            case "ginger tea":
                return new Tea("ginger");
            case "green tea":
                return new Tea("green");
            case "americano":
                return new Coffee("americano");
            case "latte":
                return new Coffee("latte");
            case "cappuccino":
                return new Coffee("cappuccino");
            case "cola":
                return new SoftDrink("cola");
            case "sprite":
                return new SoftDrink("sprite");
            case "fanta":
                return new SoftDrink("fanta");
            default:
                throw new IllegalArgumentException("Unknown drink type: " + drinkType);
        }
    }
}