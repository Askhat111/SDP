package observer;

public interface OrderObserver {
    void update(Order order, OrderStatus status);
}