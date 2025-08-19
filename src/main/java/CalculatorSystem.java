import ListOfProducts.Products;
import Order.Order;
import delivery.DeliveryParameters;
import delivery.DeliveryPrice;
import delivery.factory.DeliveryFactory;
import delivery.factory.DeliveryFactoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CalculatorSystem {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorSystem.class);

    public static void main(String[] args) {
        logger.info("Starting Delivery Calculator System");

        try {
            Products laptop = new Products("P1", "Laptop", 2.5, 1200.0);
            Products mouse = new Products("P2", "Wireless Mouse", 0.2, 25.99);
            Products keyboard = new Products("P3", "Mechanical Keyboard", 1.1, 89.99);
            Products monitor = new Products("P4", " Monitor", 5.0, 249.99);
            Products headphones = new Products("P5", "Bluetooth Headphones", 0.3, 79.99);

            createAndProcessOrder("ORD-001",
                    List.of(laptop, mouse),
                    50.0,
                    "Email"
            );

            createAndProcessOrder("ORD-002",
                    List.of(keyboard, mouse, headphones),
                    150.0,
                    "Courier"
            );

            createAndProcessOrder("ORD-003",
                    List.of(monitor, laptop, keyboard),
                    10.0,
                    "Pickup"
            );

            logger.info("All orders processed successfully");

        } catch (Exception e) {
            logger.error("An error occurred in the delivery calculator system", e);
        }
    }

    private static void createAndProcessOrder(String orderId, List<Products> products, double distance, String deliveryType) {
        try {
            double totalWeight = products.stream()
                    .mapToDouble(Products::getWeight)
                    .sum();


            DeliveryPrice deliveryStrategy = createDeliveryStrategy(deliveryType, distance, totalWeight);


            Order order = new Order(deliveryStrategy, orderId, products, distance);


            logOrderDetails(order);

        } catch (IllegalArgumentException e) {
            logger.error("Error processing order {}: {}", orderId, e.getMessage());
            throw e;
        }
    }

    private static DeliveryPrice createDeliveryStrategy(String deliveryType, double distance, double totalWeight) {

        DeliveryParameters params = new DeliveryParameters() {
            @Override
            public double getDistance() {
                if(distance < 0){
                    throw new IllegalArgumentException("Distance cannot be negative");
                }
                return distance;
            }

            @Override
            public double getWeight() {
                if (totalWeight <= 0){
                    throw new IllegalArgumentException("Weight must be positive");
                }
                return totalWeight;
            }
        };


        DeliveryFactory factory = DeliveryFactoryProvider.getFactory(deliveryType);
        return factory.createDelivery(params);
    }

    private static void logOrderDetails(Order order) {
        logger.info("=== Order Details ===");
        logger.info("Order ID: {}", order.getOrderId());
        logger.info("Delivery Distance: {} km", order.getDistance());

        logger.info("Products:");
        order.getProducts().forEach(product ->
                logger.info("  - {} (ID: {}): Rub: {} x {} kg",
                        product.getName(),
                        product.getId(),
                        product.getPrice(),
                        product.getWeight())
        );

        logger.info("Order Summary:");
        logger.info("  - Total Weight: {} kg", order.getTotalWeight());
        logger.info("  - Products Total: Rub: {}", order.getProductsTotalPrice());
        logger.info("  - Delivery Price: Rub: {}", order.getDeliveryPrice());
        logger.info("  - Order Total: Rub: {}", order.calculateTotal());
        logger.info("====================\n");
    }
}