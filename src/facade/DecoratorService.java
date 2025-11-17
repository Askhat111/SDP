package facade;
import decorator.CheeseTopping;
import decorator.DrinkCombo;
import decorator.LargeSize;
import decorator.SpicyOption;
import model.Meal;
import java.util.Scanner;

public class DecoratorService{
    public Meal applyFoodDecorators(Meal meal, Scanner scanner){
        System.out.println("Add extra cheese?");
        if (yes(scanner)) {
            meal = new CheeseTopping(meal);
        }
        System.out.println("Make it large size?");
        if (yes(scanner)) {
            meal = new LargeSize(meal);
        }
        System.out.println("Make it spicy?");
        if (yes(scanner)) {
            meal = new SpicyOption(meal);
        }
        System.out.println("Add drink combo?");
        if (yes(scanner)) {
            meal = new DrinkCombo(meal);
        }
        return meal;
    }

    private boolean yes(Scanner scanner) {
        String line = scanner.nextLine().trim().toLowerCase();
        return line.startsWith("y");
    }
}
