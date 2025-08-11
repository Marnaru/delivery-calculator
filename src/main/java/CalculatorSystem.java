import delivery.strategy.CourierDelivery;
import delivery.strategy.EmailDelivery;
import delivery.strategy.Order;
import delivery.strategy.PickUpDelivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static features.CalculateDistanceWeight.calculateDeliveryCost;

public class CalculatorSystem {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorSystem.class);


    public static void main(String[] args) {

        logger.info("Starting Delivery Calculator System");

        try{

            Order courierOrder = new Order(new CourierDelivery(50, 5));
            logOrderDetails("Standard courier delivery (50km, 5kg)", courierOrder, 1000);


            Order courierOrder1 = new Order(new CourierDelivery(150, 5));
            logOrderDetails("Long distance courier delivery (150km, 5kg)", courierOrder1, 1000);


            Order courierOrder2 = new Order(new CourierDelivery(50, 50));
            logOrderDetails("Heavy weight courier delivery (50km, 50kg)", courierOrder2, 1000);

            Order courierOrder3 = new Order(new CourierDelivery(150, 50));
            logOrderDetails("Long distance and heavy weight courier delivery (150km, 50kg)", courierOrder3, 1000);

            Order pickUp = new Order(new PickUpDelivery());
            logOrderDetails("Pickup delivery", pickUp, 1000);


            Order email = new Order(new EmailDelivery());
            logOrderDetails("Email delivery", email, 1000);

            logger.info("All delivery calculations completed successfully");
        } catch (Exception e) {
            logger.error("An error occurred in the delivery calculator system", e);
        }

        }

    private static void logOrderDetails(String description, Order order, int productPrice) {
        CalculatorSystem.logger.debug("Processing order: {}", description);
        int deliveryPrice = order.getDeliveryPrice();
        int total = order.calculateTotal(productPrice);

        CalculatorSystem.logger.info("{} - Delivery Price: {} - Total: {}", description, deliveryPrice, total);
    }
}

