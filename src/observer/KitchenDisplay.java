package observer;

public class KitchenDisplay implements OrderObserver {
    public void update(Order order) {
        System.out.println("KITCHEN: Order " + order.getOrderId() + " is " + order.getStatus());
    }
}