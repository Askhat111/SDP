package decorator;

import model.Meal;

public abstract class MealDecorator implements Meal{
    protected final Meal baseMeal;

    protected MealDecorator(Meal baseMeal){
        this.baseMeal=baseMeal;
    }

    @Override
    public String getName(){
        return baseMeal.getName();
    }
    @Override
    public double getPrice(){
        return baseMeal.getPrice();
    }
    @Override
    public String getDescription(){
        return baseMeal.getDescription();
    }
}
