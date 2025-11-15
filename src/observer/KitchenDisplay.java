package observer;

public class KitchenDisplay implements OrderObserver {
    @Override
    public void update(Order order, OrderStatus status) {
        switch (status) {
            case CONFIRMED:
                System.out.println("KITCHEN: New order " + order.getOrderId());
                break;
            case PREPARING:
                System.out.println("KITCHEN: Cooking order " + order.getOrderId());
                displayOrderItems(order);
                break;
            case READY:
                System.out.println("KITCHEN: Order " + order.getOrderId() + " ready");
                break;
        }
    }

    private void displayOrderItems(Order order) {
        if (!order.isEmpty()) {
            System.out.println("Items to prepare:");
            order.getMeals().forEach(meal ->
                    System.out.println(" - " + meal.getDescription())
            );
        }
    }
}