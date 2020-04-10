package com.javabom.producercomsumer.producercomsumer.event;

public enum PayEventType {
    CASH(CashPayEvent.class),
    CARD(CardPayEvent.class);

    private Class<?> payEventType;

    PayEventType(Class<?> payEventType) {
        this.payEventType = payEventType;
    }

    public Class<?> getPayEventType() {
        return payEventType;
    }
}
