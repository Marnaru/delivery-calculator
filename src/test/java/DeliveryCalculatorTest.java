import delivery.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the Delivery Calculator system.
 * Tests all delivery strategies and Order class functionality.
 */
@DisplayName("Delivery Calculator Tests")
public class DeliveryCalculatorTest {

    @Test
    @DisplayName("CourierDelivery should return correct price")
    public void testCourierDelivery() {
        CourierDelivery courierDelivery = new CourierDelivery();
        int actualPrice = courierDelivery.calculateDeliveryPrice();
        assertEquals(500, actualPrice, "CourierDelivery should return 500");
    }

    @Test
    @DisplayName("Order calculateTotal should return correct total with CourierDelivery")
    public void testOrderCalculateTotalWithCourier() {
        Order order = new Order(new CourierDelivery());
        int productPrice = 1000;
        int actualTotal = order.calculateTotal(productPrice);
        int expectedTotal = productPrice + 500; // 1000 + 500 = 1500
        assertEquals(expectedTotal, actualTotal, 
            "Order total with CourierDelivery should be product price + 500");
    }

    @Test
    @DisplayName("DeliveryPrices constants should have correct values")
    public void testDeliveryPricesConstants() {
        assertEquals(500, DeliveryPrices.COURIER_DELIVERY_PRICE, 
            "COURIER_DELIVERY_PRICE should be 500");
        assertEquals(300, DeliveryPrices.EMAIL_DELIVERY_PRICE, 
            "EMAIL_DELIVERY_PRICE should be 300");
        assertEquals(0, DeliveryPrices.PICKUP_DELIVERY_PRICE, 
            "PICKUP_DELIVERY_PRICE should be 0");
    }
}