package main;

import model.Meal;
import factory.MealFactory;

import java.util.Scanner;

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int categoryChoice;

        System.out.println("Welcome to the Restaurant Ordering System!");

        while (true) {
            System.out.println("\nChoose a category:");
            System.out.println("1. Food");
            System.out.println("2. Drinks");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            categoryChoice = scanner.nextInt();
            scanner.nextLine();

            if (categoryChoice == 1) {
                System.out.println("Choose a food item:");
                System.out.println("1. Pizza");
                System.out.println("2. Burger");
                System.out.println("3. Salad");
                System.out.println("4. Beshbarmak");
                System.out.println("5. Shashlik");
                System.out.println("6. Chicken");
                System.out.println("7. Manti");
                System.out.println("8. Pilau");
                System.out.print("Which food do you want?: ");

                String foodChoice = scanner.nextLine();

                Meal meal = MealFactory.createFood(foodChoice.toLowerCase());

                System.out.println("\nYou have chosen: " + meal.getName());
                System.out.println("Description: " + meal.getDescription());
                System.out.println("Price: $" + meal.getPrice());

            } else if (categoryChoice == 2) {
                System.out.println("Choose a drink:");
                System.out.println("1. Moroccan Tea");
                System.out.println("2. Ginger Tea");
                System.out.println("3. Green Tea");
                System.out.println("4. Americano");
                System.out.println("5. Latte");
                System.out.println("6. Cappuccino");
                System.out.println("7. Cola");
                System.out.println("8. Sprite");
                System.out.println("9. Fanta");
                System.out.print("Which drink do you want with food?: ");

                String drinkChoice = scanner.nextLine();

                Meal drink = MealFactory.createDrink(drinkChoice.toLowerCase());

                System.out.println("\nYou have chosen: " + drink.getName());
                System.out.println("Description: " + drink.getDescription());
                System.out.println("Price: $" + drink.getPrice());

            } else if (categoryChoice == 3) {
                System.out.println("Thank you for visiting! Goodbye.");
                break;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}