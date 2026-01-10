package ru.binarysimple.notification.model;

public enum NotificationType {
    INFO("Info"),
    WARNING("Warning"),
    ERROR("Error"),
    SUCCESS("Success"),
    DEFAULT("Default");

    private final String typeName;

    NotificationType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
