package facade;
import model.Meal;
import strategy.*;
import java.util.List;
import java.util.Scanner;

public class PricingManager{
    private PricingStrategy pricingStrategy;

    public PricingManager(){
        this.pricingStrategy = null;
    }
    public PricingStrategy getStrategy(){
        return pricingStrategy;
    }
    public void clearStrategy(){
        pricingStrategy = null;
    }

    public void chooseStrategy(Scanner scanner){
        System.out.println("Choose a pricing strategy:");
        System.out.println("1. Standard Pricing (0% discount)");
        System.out.println("2. Student Discount (10% discount)");
        System.out.println("3. Happy Hour Pricing (50% discount)");
        System.out.println("4. Birthday Discount (20% discount)");
        System.out.println("5. Loyalty Discount (15% discount)");
        System.out.print("Enter your choice: ");

        int choice = readInt(scanner);
        switch (choice){
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
                int orders = readInt(scanner);
                pricingStrategy = new LoyaltyDiscountPricing(orders);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Standard Pricing.");
                pricingStrategy = new StandardPricing();
        }
    }

    public double calculateTotalWithStrategy(List<Meal> items){
        if (pricingStrategy == null) {
            throw new IllegalStateException("Pricing strategy is not selected");
        }
        double total = 0.0;
        for (Meal meal : items) {
            total += pricingStrategy.calculatePrice(meal);
        }
        return total;
    }
    private int readInt(Scanner scanner){
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }
}
