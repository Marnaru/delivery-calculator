package delivery.factory;

import delivery.DeliveryPrice;
import delivery.DeliveryParameters;
import delivery.strategy.EmailDelivery;

public class EmailDeliveryFactory implements DeliveryFactory {
    @Override
    public DeliveryPrice createDelivery(DeliveryParameters params) {
        return new EmailDelivery();
    }
}