import delivery.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Delivery Strategy Parameterized Tests")
public class ParametrizedDeliveryTest {

    @ParameterizedTest(name = "{index} => Strategy: {0}, Distance: {1}km, Weight: {2}kg, Expected: {3}")
    @MethodSource("deliveryScenarios")
    @DisplayName("Test delivery strategies with various scenarios")
    void testDeliveryStrategies(DeliveryPrice strategy, double distance, double weight, int expectedTotal) {

        if (strategy instanceof CourierDelivery) {
            strategy = new CourierDelivery(distance, weight);
        }

        Order order = new Order(strategy);
        int actualTotal = order.calculateTotal(1000); // Using 1000 as base product price

        assertEquals(expectedTotal, actualTotal,
                String.format("Incorrect total for %s with distance=%.1fkm, weight=%.1fkg",
                        strategy.getClass().getSimpleName(), distance, weight));
    }

    private static Stream<Arguments> deliveryScenarios() {
        return Stream.of(
                // CourierDelivery scenarios
                Arguments.of(new CourierDelivery(50, 5), 50, 5, 1500),   // Base price
                Arguments.of(new CourierDelivery(150, 5), 150, 5, 1650), // Long distance
                Arguments.of(new CourierDelivery(50, 50), 50, 50, 1600), // Heavy weight
                Arguments.of(new CourierDelivery(150, 50), 150, 50, 1780), // Both surcharges

                // EmailDelivery (distance and weight don't affect the price)
                Arguments.of(new EmailDelivery(), 0, 0, 1300),  // 1000 + 300
                Arguments.of(new EmailDelivery(), 100, 100, 1300), // Still 1000 + 300

                // PickUpDelivery (always free)
                Arguments.of(new PickUpDelivery(), 0, 0, 1000),    // 1000 + 0
                Arguments.of(new PickUpDelivery(), 100, 100, 1000) // Still 1000 + 0
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCourierDeliveryParameters")
    @DisplayName("Test CourierDelivery with invalid parameters")
    void testCourierDeliveryWithInvalidParameters(double distance, double weight, String expectedMessage) {
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new CourierDelivery(distance, weight)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> invalidCourierDeliveryParameters() {
        return Stream.of(
                Arguments.of(-1, 5, "Distance must be non-negative and weight must be positive"),
                Arguments.of(50, 0, "Distance must be non-negative and weight must be positive"),
                Arguments.of(-100, -10, "Distance must be non-negative and weight must be positive")
        );
    }
}