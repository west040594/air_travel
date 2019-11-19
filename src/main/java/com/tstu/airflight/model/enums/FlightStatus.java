package com.tstu.airflight.model.enums;


public enum  FlightStatus {
    WAIT("Ожидание"),
    PROGRESS("В пути"),
    CANCELED("Отменен"),
    SUCCESS("Завершен");

    private String value;

    FlightStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
