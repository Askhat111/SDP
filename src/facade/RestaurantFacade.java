package facade;

import factory.MealFactory;
import model.Meal;
import observer.OrderStatus;
import java.util.Scanner;
public class RestaurantFacade{

    private final Scanner scanner;
    private final OrderManager orderManager;
    private final PricingManager pricingManager;
    private final DecoratorService decoratorService;
    private final PaymentService paymentService;
    private double totalWithStrategy;

    public RestaurantFacade(){
        this.scanner = new Scanner(System.in);
        this.orderManager = new OrderManager();
        this.pricingManager = new PricingManager();
        this.decoratorService = new DecoratorService();
        this.paymentService = new PaymentService(scanner);
        this.totalWithStrategy = 0.0;
    }

    public void start(){
        System.out.println("Welcome to the 'Mokki' Restaurant!");
        while (true) {
            int category = chooseCategory();
            if (category == 3){
                if (!orderManager.isEmpty()) {
                    System.out.println("You have items in your order.");
                    System.out.print("Do you want to pay before exit?: ");
                    if (yes()){
                        processPayment();
                    }
                }
                System.out.println("Thank you for visiting 'Mokki' Restaurant! Goodbye.");
                break;
            }
            if (category == 1){
                handleFood();
            } else if (category == 2){
                handleDrink();
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private int chooseCategory(){
        System.out.println("Choose a category:");
        System.out.println("1. Food");
        System.out.println("2. Drinks");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return readInt();
    }

    private void handleFood(){
        System.out.println("Choose a food:");
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
        meal = decoratorService.applyFoodDecorators(meal, scanner);
        addItemToOrder(meal);
    }
    private void handleDrink(){
        System.out.println("Choose a drink:");
        System.out.println("1. Tary Tea");
        System.out.println("2. Berry Tea");
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

    private void addItemToOrder(Meal meal){
        orderManager.addMeal(meal);
        double basePrice = meal.getPrice();

        System.out.println("\nAdded to order: " + meal.getName());
        System.out.println("Description: " + meal.getDescription());
        System.out.println("Item base price: $" + basePrice);
        System.out.println("\nCurrent order:");
        System.out.println("Items: " + orderManager.getItemCount());
        System.out.println("Base subtotal: $" + orderManager.getBaseSubtotal());
        System.out.print("Do you want to pay now?: ");
        if (yes()) {
            processPayment();
        }
    }

    private void processPayment(){
        if (orderManager.isEmpty()) {
            System.out.println("Your order is empty.");
            return;
        }
        if (pricingManager.getStrategy() == null) {
            pricingManager.chooseStrategy(scanner);
        }

        double baseSubtotal = orderManager.getBaseSubtotal();
        totalWithStrategy = pricingManager.calculateTotalWithStrategy(orderManager.getItems());
        double discountAmount = baseSubtotal - totalWithStrategy;
        double discountPercent = (discountAmount / baseSubtotal) * 100;

        System.out.println("\nFinal order summary:");
        System.out.println("Items: " + orderManager.getItemCount());
        System.out.println("Base subtotal: $" + baseSubtotal);
        System.out.println("Discount: " + String.format("%.1f", discountPercent) + "%");
        System.out.println("Final price (" + pricingManager.getStrategy().getStrategyName() + "): $" + totalWithStrategy);
        boolean success = paymentService.pay(totalWithStrategy, orderManager.getOrder());

        if (success) {
            orderManager.getOrder().setStatus(OrderStatus.CONFIRMED);
            orderManager.getOrder().setStatus(OrderStatus.PREPARING);
            orderManager.getOrder().setStatus(OrderStatus.READY);
            orderManager.getOrder().setStatus(OrderStatus.COMPLETED);
            orderManager.reset();
            pricingManager.clearStrategy();
            totalWithStrategy = 0.0;
        }
    }

    private String normalizeFoodChoice(String input){
        String value = input.trim().toLowerCase();
        switch (value){
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
            case "1": return "tary tea";
            case "2": return "berry tea";
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
        String line = readLine().trim().toLowerCase();
        return line.startsWith("y");
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
