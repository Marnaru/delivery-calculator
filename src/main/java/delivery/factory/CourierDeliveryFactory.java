package delivery.factory;

import delivery.strategy.CourierDelivery;
import delivery.DeliveryPrice;
import delivery.DeliveryParameters;

public class CourierDeliveryFactory implements DeliveryFactory {
    @Override
    public DeliveryPrice createDelivery(DeliveryParameters params) {
        return new CourierDelivery(params);
    }
}

