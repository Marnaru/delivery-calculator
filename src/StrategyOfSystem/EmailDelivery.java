package StrategyOfSystem;

public class EmailDelivery implements DeliveryPrice {
    @Override
    public int calculateDeliveryPrice() {
        return 300;
    }
}
