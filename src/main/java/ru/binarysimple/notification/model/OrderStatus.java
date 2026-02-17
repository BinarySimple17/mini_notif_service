package ru.binarysimple.notification.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    NEW("New"),
    DELIVERY_FAILED("Delivery failed"),
    INSUFFICIENT_FUNDS("Insufficient funds"),
    FAILED("Failed"),
    IN_PROGRESS("In progress"),
    PAID("Paid"),
    WAREHOUSE_RESERVED("Warehouse reserved"),
    DONE("Done"),
    CANCELED("Canceled"),
    COMPENSATED("Compensated"),
    RESERVING_PAYMENT("Reserving payment"),
    STARTED("Started"),
    PAYMENT_PROCESSING("Payment processing"),
    PAYMENT_COMPLETED("Payment completed"),
    PAYMENT_FAILED("Payment failed"),
    WAREHOUSE_RESERVING("Warehouse stock reserving"),
    WAREHOUSE_FAILED("Warehouse reserving failed"),
    DELIVERY_SCHEDULING("Delivery scheduling"),
    DELIVERY_SCHEDULED("Delivery scheduled"),
    COMPLETED("Completed"),
    COMPENSATING("Compensating");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
