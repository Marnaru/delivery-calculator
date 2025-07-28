package StrategyOfSystem;

public class CourierDelivery implements DeliveryPrice {
    @Override
    public int calculateDeliveryPrice(){
        return 500;
    }

}
