package decorator;
import model.Meal;

public class SpicyOption extends MealDecorator{
    private static final double SPICY_EXTRA = 0.5;

    public SpicyOption(Meal baseMeal){
        super(baseMeal);
    }

    @Override
    public String getName(){
        return baseMeal.getName()+" (spicy)";
    }
    @Override
    public double getPrice(){
        return baseMeal.getPrice()+SPICY_EXTRA;
    }
    @Override
    public String getDescription(){
        return baseMeal.getDescription()+" with spicy sauce";
    }
}
