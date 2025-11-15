package observer;

import java.util.List;

public class OrderNotifier {
    public static void notifyObservers(List<OrderObserver> observers, Order order, OrderStatus status) {
        for (OrderObserver observer : observers) {
            observer.update(order, status);
        }
    }
}