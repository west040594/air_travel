package com.tstu.airflight.model.enums;

public enum TravelClass {
    ECONOMY("Эконом"),
    BUSINESS("Бизнес");

    private String value;

    TravelClass(String value) {
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
