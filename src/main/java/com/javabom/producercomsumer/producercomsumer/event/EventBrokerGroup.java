package com.javabom.producercomsumer.producercomsumer.event;

import java.util.Arrays;

public enum EventBrokerGroup {
    CARD(CardPayEvent.class, new EventBroker<CardPayEvent>()),
    CASH(CashPayEvent.class, new EventBroker<CashPayEvent>());

    private Class<? extends PayEvent> payEvent;
    private EventBroker<? extends PayEvent> payEventBroker;

    EventBrokerGroup(Class<? extends PayEvent> payEvent, EventBroker<? extends PayEvent> payEventBroker) {
        this.payEvent = payEvent;
        this.payEventBroker = payEventBroker;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PayEvent> EventBroker<T> findPayEventBroker(Class<T> payEvent) {
        return (EventBroker<T>) Arrays.stream(values())
                .filter(event -> event.getPayEvent().equals(payEvent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 EventBroker가 없습니다"))
                .getPayEventBroker();
    }

    private EventBroker<?> getPayEventBroker() {
        return payEventBroker;
    }

    private Class<? extends PayEvent> getPayEvent() {
        return payEvent;
    }

}
