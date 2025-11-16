package decorator;
import model.Meal;

public class LargeSize extends MealDecorator{
    private static final double LARGE_SIZE_EXTRA = 3.0;

    public LargeSize(Meal baseMeal){
        super(baseMeal);
    }

    @Override
    public String getName(){
        return baseMeal.getName()+" (large)";
    }
    @Override
    public double getPrice(){
        return baseMeal.getPrice()+LARGE_SIZE_EXTRA;
    }
    @Override
    public String getDescription(){
        return baseMeal.getDescription()+" in large size";
    }
}

