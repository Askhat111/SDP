package restaurant;

import restaurant.factory.*;

public class Main {
    public static void main(String[] args) {
        MealFactory factory = new MealFactory();

        System.out.println(factory.createMeal(MealType.PIZZA).getName() + " - " + factory.createMeal(MealType.PIZZA).getPrice() + "₸");
        System.out.println(factory.createMeal(MealType.BURGER).getName() + " - " + factory.createMeal(MealType.BURGER).getPrice() + "₸");
        System.out.println(factory.createMeal(MealType.SALAD).getName() + " - " + factory.createMeal(MealType.SALAD).getPrice() + "₸");
    }
}