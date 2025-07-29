import delivery.strategy.CourierDelivery;
import delivery.strategy.EmailDelivery;
import delivery.strategy.Order;
import delivery.strategy.PickUpDelivery;

public class CalculatorSystem {
    public static void main(String[] args) {

        Order courier = new Order(new CourierDelivery());
        System.out.println("Courier delivery: " + courier.getDeliveryPrice());

        int totalCourier = courier.calculateTotal(1000);
        System.out.println("Total: " + totalCourier);


        Order pickUp = new Order(new PickUpDelivery());
        System.out.println("Pickup delivery: " + pickUp.getDeliveryPrice());

        int totalPickUp = pickUp.calculateTotal(1000);
        System.out.println("Total: " + totalPickUp);


        Order email = new Order(new EmailDelivery());
        System.out.println("Email delivery: " + email.getDeliveryPrice());

        int totalEmail = email.calculateTotal(1000);
        System.out.println("Total: " + totalEmail);

    }
}
