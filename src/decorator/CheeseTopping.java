package decorator;
import model.Meal;

public class CheeseTopping extends MealDecorator{
    private static final double CHEESE_PRICE=1.5;

    public CheeseTopping(Meal baseMeal){
        super(baseMeal);
    }

    @Override
    public String getName(){
        return baseMeal.getName()+" + cheese";
    }
    @Override
    public double getPrice(){
        return baseMeal.getPrice()+CHEESE_PRICE;
    }
    @Override
    public String getDescription(){
        return baseMeal.getDescription()+" with extra cheese";
    }
}
