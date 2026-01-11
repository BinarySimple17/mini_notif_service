package ru.binarysimple.notification.model;

public enum ParentType {
    ORDER("Order"),
    PAYMENT("Payment"),
    USER("User");

    private final String parentName;

    ParentType(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return parentName;
    }
}
