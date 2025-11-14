package main;

import model.Meal;
import factory.MealFactory;
import strategy.PricingStrategy;
import strategy.StandardPricing;
import strategy.StudentDiscountPricing;
import strategy.HappyHourPricing;
import strategy.BirthdayDiscountPricing;
import strategy.LoyaltyDiscountPricing;

import java.util.Scanner;

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int categoryChoice;
        PricingStrategy pricingStrategy = null;

        System.out.println("Welcome to the Restaurant Ordering System!");

        System.out.println("Choose a pricing strategy:");
        System.out.println("1. Standard Pricing");
        System.out.println("2. Student Discount");
        System.out.println("3. Happy Hour Pricing");
        System.out.println("4. Birthday Discount");
        System.out.println("5. Loyalty Discount");
        System.out.print("Enter your choice: ");
        int pricingChoice = scanner.nextInt();
        scanner.nextLine();

        switch (pricingChoice) {
            case 1:
                pricingStrategy = new StandardPricing();
                break;
            case 2:
                pricingStrategy = new StudentDiscountPricing();
                break;
            case 3:
                pricingStrategy = new HappyHourPricing();
                break;
            case 4:
                pricingStrategy = new BirthdayDiscountPricing();
                break;
            case 5:
                System.out.print("Enter the number of previous orders: ");
                int orders = scanner.nextInt();
                pricingStrategy = new LoyaltyDiscountPricing(orders);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Standard Pricing.");
                pricingStrategy = new StandardPricing();
        }

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

                double price = pricingStrategy.calculatePrice(meal);

                System.out.println("\nYou have chosen: " + meal.getName());
                System.out.println("Description: " + meal.getDescription());
                System.out.println("Price: $" + price);
                System.out.println("Your Pricing Strategy: " + pricingStrategy.getStrategyName());

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
                System.out.print("Which drink do you want?: ");

                String drinkChoice = scanner.nextLine();
                Meal drink = MealFactory.createDrink(drinkChoice.toLowerCase());

                double price = pricingStrategy.calculatePrice(drink);

                System.out.println("\nYou have chosen: " + drink.getName());
                System.out.println("Description: " + drink.getDescription());
                System.out.println("Price: $" + price);
                System.out.println("Pricing Strategy: " + pricingStrategy.getStrategyName());

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