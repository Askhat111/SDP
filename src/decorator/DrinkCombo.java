package decorator;
import model.Meal;

public class DrinkCombo extends MealDecorator{
    private static final double DRINK_PRICE = 2.0;

    public DrinkCombo(Meal baseMeal){
        super(baseMeal);
    }

    @Override
    public String getName(){
        return baseMeal.getName()+" + drink combo";
    }
    @Override
    public double getPrice(){
        return baseMeal.getPrice()+DRINK_PRICE;
    }
    @Override
    public String getDescription(){
        return baseMeal.getDescription()+" served with a drink";
    }
}
