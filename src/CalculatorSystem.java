import StrategyOfSystem.CourierDelivery;
import StrategyOfSystem.EmailDelivery;
import StrategyOfSystem.Order;
import StrategyOfSystem.PickUpDelivery;

public class CalculatorSystem {
    public static void main(String[] args) {

        Order courier = new Order(new CourierDelivery());
        System.out.println("Deliver: " + courier.getDeliveryPrice());

        int totalCourier = courier.calculateTotal(1000);
        System.out.println("Total: " + totalCourier);


        Order pickUp = new Order(new PickUpDelivery());
        System.out.println("Deliver: " + pickUp.getDeliveryPrice());

        int totalPickUp = pickUp.calculateTotal(1000);
        System.out.println("Total: " + totalPickUp);


        Order email = new Order(new EmailDelivery());
        System.out.println("Deliver: " + email.getDeliveryPrice());

        int totalEmail = email.calculateTotal(1000);
        System.out.println("Total: " + totalEmail);

    }
}
