package delivery.factory;

import delivery.DeliveryPrice;
import delivery.DeliveryParameters;

public interface DeliveryFactory {
    DeliveryPrice createDelivery(DeliveryParameters params);
}