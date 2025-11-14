package observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = "NEW";
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getStatus() { return status; }
    public String getOrderId() { return orderId; }
}