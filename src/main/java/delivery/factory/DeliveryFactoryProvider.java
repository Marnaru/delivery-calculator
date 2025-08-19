package delivery.factory;

import java.util.HashMap;
import java.util.Map;

public class DeliveryFactoryProvider {
    private static final Map<String, DeliveryFactory> FACTORIES = new HashMap<>();

    static {
        registerFactory("courier", new CourierDeliveryFactory());
        registerFactory("email", new EmailDeliveryFactory());
        registerFactory("pickup", new PickUpDeliveryFactory());
    }

    public static void registerFactory(String type, DeliveryFactory factory) {
        if (type == null || factory == null) {
            throw new IllegalArgumentException("Type and factory cannot be null");
        }
        FACTORIES.put(type.toLowerCase(), factory);
    }

    public static DeliveryFactory getFactory(String deliveryType) {
        if (deliveryType == null) {
            throw new IllegalArgumentException("Delivery type cannot be null");
        }
        DeliveryFactory factory = FACTORIES.get(deliveryType.toLowerCase());
        if (factory == null) {
            throw new IllegalArgumentException("Unknown delivery type: " + deliveryType);
        }
        return factory;
    }
}