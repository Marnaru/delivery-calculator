package delivery.factory;

import delivery.DeliveryPrice;
import delivery.DeliveryParameters;
import delivery.strategy.PickUpDelivery;

public class PickUpDeliveryFactory implements DeliveryFactory {
    @Override
    public DeliveryPrice createDelivery(DeliveryParameters params) {
        return new PickUpDelivery();
    }
}