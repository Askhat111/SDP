package facade;

import model.Meal;
import observer.CustomerApp;
import observer.KitchenDisplay;
import observer.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderManager{
    private Order order;
    private final List<Meal> items;

    public OrderManager(){
        this.items = new ArrayList<>();
        reset();
    }
    public void addMeal(Meal meal){
        order.addMeal(meal);
        items.add(meal);
    }
    public boolean isEmpty(){
        return order.getItemCount() == 0;
    }
    public int getItemCount(){
        return order.getItemCount();
    }
    public double getBaseSubtotal(){
        return order.getSubtotal();
    }
    public List<Meal> getItems(){
        return items;
    }
    public Order getOrder(){
        return order;
    }
    public void reset(){
        this.order = new Order();
        this.order.addObserver(new KitchenDisplay());
        this.order.addObserver(new CustomerApp("Customer"));
        this.items.clear();
    }
}
