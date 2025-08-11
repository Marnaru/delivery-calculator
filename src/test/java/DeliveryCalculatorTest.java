import delivery.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the Delivery Calculator system.
 * Tests all delivery strategies and Order class functionality.
 */
@DisplayName("Delivery Calculator Tests")
public class DeliveryCalculatorTest {

    @Test
    @DisplayName("CourierDelivery should return correct price")
    public void testCourierDeliveryWithNormalDistanceAndWeight() {
        CourierDelivery courierDelivery = new CourierDelivery(50, 5);
        int actualPrice = courierDelivery.calculateDeliveryPrice();
        assertEquals(500, actualPrice, "CourierDelivery should return 500");
    }

    @Test
    @DisplayName("CourierDelivery should return correct price")
    public void testCourierDeliveryWithLongDistanceandNormalWeight() {
        CourierDelivery courierDelivery = new CourierDelivery(150, 5);
        int actualPrice = courierDelivery.calculateDeliveryPrice();
        assertEquals(500, actualPrice, "CourierDelivery should return 500");
    }


    @Test
    @DisplayName("CourierDelivery should return correct price")
    public void testCourierDeliveryWithNormalDistanceAndHeavyWeight() {
        CourierDelivery courierDelivery = new CourierDelivery(50, 50);
        int actualPrice = courierDelivery.calculateDeliveryPrice();
        assertEquals(500, actualPrice, "CourierDelivery should return 500");
    }

    @Test
    @DisplayName("CourierDelivery should return correct price")
    public void testCourierDeliveryWithLongDistanceAndHeavyWeight() {
        CourierDelivery courierDelivery = new CourierDelivery(150, 50);
        int actualPrice = courierDelivery.calculateDeliveryPrice();
        assertEquals(500, actualPrice, "CourierDelivery should return 500");
    }

    @Test
    @DisplayName("EmailDelivery should return correct price")
    public void testEmailDelivery() {
        EmailDelivery email = new EmailDelivery();
        int actualPrice = email.calculateDeliveryPrice();
        assertEquals(300, actualPrice, "EmailDelivery should return 300");
    }

    @Test
    @DisplayName("PickUpDelivery should return correct price")
    public void testPickUpDelivery() {
        PickUpDelivery pickUp = new PickUpDelivery();
        int actualPrice = pickUp.calculateDeliveryPrice();
        assertEquals(0, actualPrice, "PickUpDelivery should return 0");
    }

    @Test
    @DisplayName("Order calculateTotal should return correct total with CourierDelivery")
    public void testOrderCalculateTotalWithCourier() {
        Order order = new Order(new CourierDelivery(50, 5));
        int productPrice = 1000;
        int actualTotal = order.calculateTotal(productPrice);
        int expectedTotal = productPrice + 500; // 1000 + 500 = 1500
        assertEquals(expectedTotal, actualTotal, 
            "Order total with CourierDelivery should be product price + 500");
    }

    @Test
    @DisplayName("Order calculateTotal should return correct total with EmailDelivery")
    public void testOrderCalculateTotalWithEmail() {
        Order order = new Order(new EmailDelivery());
        int productPrice = 1000;
        int actualTotal = order.calculateTotal(productPrice);
        int expectedTotal = productPrice + 300; // 1000 + 300 = 1300
        assertEquals(expectedTotal, actualTotal,
                "Order total with EmailDelivery should be product price + 300");
    }

    @Test
    @DisplayName("Order calculateTotal should return correct total with PickUpDelivery")
    public void testOrderCalculateTotalWithPickUp() {
        Order order = new Order(new PickUpDelivery());
        int productPrice = 1000;
        int actualTotal = order.calculateTotal(productPrice);
        int expectedTotal = productPrice + 0; // 1000 + 0 = 1000
        assertEquals(expectedTotal, actualTotal,
                "Order total with PickUpDelivery should be product price + 0");
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

    @Test
    @DisplayName("Should not be instantiable and throw AssertionError")
    void shouldNotBeInstantiable() throws Exception{
        Constructor<DeliveryPrices> constructor = DeliveryPrices.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof AssertionError);
        assertEquals("DeliveryPrices is a utility class and should not be instantiated", exception.getCause().getMessage());
    }

    @Test
    @DisplayName("Order constructor should throw IllegalArgumentException when deliveryPrice is null")
    void shouldThrowExceptionWhenDeliveryPriceIsNull(){
        DeliveryPrice nullDeliveryPrice = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Order(nullDeliveryPrice), "Expected constructor to throw IllegalArgumentException for null deliveryPrice");

        assertEquals("DeliveryPrice cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("calculateTotal should throw IllegalArgumentException when product price is negative")
    void shouldThrowExceptionWhenProductPriceIsNegative() {
        Order order = new Order(new CourierDelivery(50, 5));
        int negativePrice = -100;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> order.calculateTotal(negativePrice),
                "Expected calculateTotal to throw for negative price"
        );

        assertEquals("Product price cannot be negative", exception.getMessage());
    }


    @Test
    @DisplayName("Feature is working correctly")
    void calculateDeliveryPrice_shouldUseDistanceAndWeight() {

        double distance = 150;
        double weight = 15;
        CourierDelivery delivery = new CourierDelivery(distance, weight);

        int price = delivery.calculateDeliveryPrice();

        assertEquals(780, price, "Expected price to be 780 (500 * 1.2 * 1.3) for distance=150, weight=15");
    }


    @Nested
    @DisplayName("CalculatorSystem Main Class Tests")
    public class CalculatorSystemTest {
        private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        private final PrintStream standardOut = System.out;

        @BeforeEach
        public void setUp() {
            System.setOut(new PrintStream(outputStreamCaptor));
        }

        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }

        @Test
        @DisplayName("main method should output correct delivery prices and totals")
        void main_ShouldOutputCorrectDeliveryInfo() {

            CalculatorSystem.main(new String[]{});

            String output = outputStreamCaptor.toString();

            assertTrue(output.contains("Standard courier delivery (50km, 5kg) - Delivery Price: 500 - Total: 1500"),
                    "Should log standard courier delivery with correct price and total");
            assertTrue(output.contains("Long distance courier delivery (150km, 5kg) - Delivery Price: 650 - Total: 1650"),
                    "Should log long distance courier delivery with correct price and total");
            assertTrue(output.contains("Heavy weight courier delivery (50km, 50kg) - Delivery Price: 600 - Total: 1600"),
                    "Should log heavy weight courier delivery with correct price and total");
            assertTrue(output.contains("Long distance and heavy weight courier delivery (150km, 50kg) - Delivery Price: 780 - Total: 1780"),
                    "Should log long distance + heavy weight courier delivery with correct price and total");
            assertTrue(output.contains("Pickup delivery - Delivery Price: 0 - Total: 1000"),
                    "Should log pickup delivery with correct price and total");
            assertTrue(output.contains("Email delivery - Delivery Price: 300 - Total: 1300"),
                    "Should log email delivery with correct price and total");
        }
    }

}