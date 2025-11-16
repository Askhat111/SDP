package observer;

import model.Meal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final List<OrderObserver> observers;
    private final List<Meal> meals;
    private OrderStatus status;

    public Order() {
        this.orderId = "ORD-" + (10000 + (int)(Math.random() * 90000));
        this.observers = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void setStatus(OrderStatus newStatus) {
        if (this.status != newStatus) {
            this.status = newStatus;
            OrderNotifier.notifyObservers(observers, this, newStatus);
        }
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public List<Meal> getMeals() { return new ArrayList<>(meals); }
    public boolean isEmpty() { return meals.isEmpty(); }
    public double getSubtotal() { return OrderCalculator.calculateSubtotal(meals); }
    public int getItemCount() { return OrderCalculator.getItemCount(meals); }
}