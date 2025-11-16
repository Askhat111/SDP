package facade;

import adapter.ExternalPaymentAPI;
import adapter.PaymentAdapter;
import adapter.PaymentProcessor;
import decorator.CheeseTopping;
import decorator.DrinkCombo;
import decorator.LargeSize;
import decorator.SpicyOption;
import factory.MealFactory;
import model.Meal;
import observer.CustomerApp;
import observer.KitchenDisplay;
import observer.Order;
import observer.OrderStatus;
import strategy.BirthdayDiscountPricing;
import strategy.HappyHourPricing;
import strategy.LoyaltyDiscountPricing;
import strategy.PricingStrategy;
import strategy.StandardPricing;
import strategy.StudentDiscountPricing;

import java.util.Scanner;

public class RestaurantFacade {

    private final Scanner scanner;
    private final PaymentProcessor paymentProcessor;

    private PricingStrategy pricingStrategy;
    private Order order;
    private double totalWithStrategy;

    public RestaurantFacade() {
        this.scanner = new Scanner(System.in);
        this.paymentProcessor = new PaymentAdapter(new ExternalPaymentAPI());
        this.order = new Order();
        this.order.addObserver(new KitchenDisplay());
        this.order.addObserver(new CustomerApp("Customer"));
        this.totalWithStrategy = 0.0;
    }

    public void start() {
        pricingStrategy = choosePricingStrategy();

        while (true) {
            int category = chooseCategory();

            if (category == 3) {
                if (order.getItemCount() > 0) {
                    System.out.println("You have items in your order.");
                    System.out.print("Do you want to pay before exit? (y/n): ");
                    if (yes()) {
                        processPayment();
                    }
                }
                System.out.println("Thank you for visiting! Goodbye.");
                break;
            }

            if (category == 1) {
                handleFood();
            } else if (category == 2) {
                handleDrink();
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private PricingStrategy choosePricingStrategy() {
        System.out.println("Welcome to the Restaurant Ordering System!");

        System.out.println("Choose a pricing strategy:");
        System.out.println("1. Standard Pricing");
        System.out.println("2. Student Discount");
        System.out.println("3. Happy Hour Pricing");
        System.out.println("4. Birthday Discount");
        System.out.println("5. Loyalty Discount");
        System.out.print("Enter your choice: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                return new StandardPricing();
            case 2:
                return new StudentDiscountPricing();
            case 3:
                return new HappyHourPricing();
            case 4:
                return new BirthdayDiscountPricing();
            case 5:
                System.out.print("Enter the number of previous orders: ");
                int orders = readInt();
                return new LoyaltyDiscountPricing(orders);
            default:
                System.out.println("Invalid choice. Defaulting to Standard Pricing.");
                return new StandardPricing();
        }
    }

    private int chooseCategory() {
        System.out.println("\nChoose a category:");
        System.out.println("1. Food");
        System.out.println("2. Drinks");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return readInt();
    }

    private void handleFood() {
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

        String input = readLine();
        String foodType = normalizeFoodChoice(input);

        Meal meal;
        try {
            meal = MealFactory.createFood(foodType);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown food item.");
            return;
        }

        meal = applyDecoratorsToFood(meal);
        addItemToOrder(meal);
    }

    private void handleDrink() {
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

        String input = readLine();
        String drinkType = normalizeDrinkChoice(input);

        Meal drink;
        try {
            drink = MealFactory.createDrink(drinkType);
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown drink.");
            return;
        }

        addItemToOrder(drink);
    }

    private void addItemToOrder(Meal meal) {
        order.addMeal(meal);

        double itemPrice = pricingStrategy.calculatePrice(meal);
        totalWithStrategy += itemPrice;

        System.out.println("\nAdded to order: " + meal.getName());
        System.out.println("Description: " + meal.getDescription());
        System.out.println("Item price with strategy: $" + itemPrice);

        System.out.println("\nCurrent order:");
        System.out.println("Items: " + order.getItemCount());
        System.out.println("Base subtotal: " + order.getSubtotal());
        System.out.println("Total with pricing strategy: $" + totalWithStrategy);

        System.out.print("Do you want to pay now? (y/n): ");
        if (yes()) {
            processPayment();
        }
    }

    private Meal applyDecoratorsToFood(Meal meal) {
        System.out.println("Add extra cheese? (y/n)");
        if (yes()) {
            meal = new CheeseTopping(meal);
        }

        System.out.println("Make it large size? (y/n)");
        if (yes()) {
            meal = new LargeSize(meal);
        }

        System.out.println("Make it spicy? (y/n)");
        if (yes()) {
            meal = new SpicyOption(meal);
        }

        System.out.println("Add drink combo? (y/n)");
        if (yes()) {
            meal = new DrinkCombo(meal);
        }

        return meal;
    }

    private void processPayment() {
        if (order.getItemCount() == 0) {
            System.out.println("Your order is empty.");
            return;
        }

        System.out.print("Enter currency (USD, EUR, KZT, GBP): ");
        String currency = readLine().trim().toUpperCase();

        if (!paymentProcessor.supportsCurrency(currency)) {
            System.out.println("Currency not supported.");
            System.out.println("Supported currencies: " + paymentProcessor.getSupportedCurrencies());
            return;
        }

        boolean success = paymentProcessor.processPayment(totalWithStrategy, order.getOrderId(), currency);

        if (success) {
            System.out.println("Payment successful via " + paymentProcessor.getProcessorName());
            order.setStatus(OrderStatus.CONFIRMED);
            order.setStatus(OrderStatus.PREPARING);
            order.setStatus(OrderStatus.READY);
            order.setStatus(OrderStatus.COMPLETED);

            order = new Order();
            order.addObserver(new KitchenDisplay());
            order.addObserver(new CustomerApp("Customer"));
            totalWithStrategy = 0.0;
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    private String normalizeFoodChoice(String input) {
        String value = input.trim().toLowerCase();
        switch (value) {
            case "1": return "pizza";
            case "2": return "burger";
            case "3": return "salad";
            case "4": return "beshbarmak";
            case "5": return "shashlik";
            case "6": return "chicken";
            case "7": return "manti";
            case "8": return "pilau";
            default: return value;
        }
    }

    private String normalizeDrinkChoice(String input) {
        String value = input.trim().toLowerCase();
        switch (value) {
            case "1": return "moroccan tea";
            case "2": return "ginger tea";
            case "3": return "green tea";
            case "4": return "americano";
            case "5": return "latte";
            case "6": return "cappuccino";
            case "7": return "cola";
            case "8": return "sprite";
            case "9": return "fanta";
            default: return value;
        }
    }

    private boolean yes() {
        String line = readLine().trim();
        return line.equalsIgnoreCase("y");
    }

    private int readInt() {
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
