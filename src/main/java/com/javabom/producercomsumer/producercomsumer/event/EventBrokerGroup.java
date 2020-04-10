package com.javabom.producercomsumer.producercomsumer.event;

import java.util.Arrays;

public enum EventBrokerGroup {
    CARD(CardPayEvent.class, new EventBroker<CardPayEvent>()),
    CASH(CashPayEvent.class, new EventBroker<CashPayEvent>());

    private Class<? extends PaymentEvent> payEvent;
    private EventBroker<? extends PaymentEvent> payEventBroker;

    EventBrokerGroup(Class<? extends PaymentEvent> payEvent, EventBroker<? extends PaymentEvent> payEventBroker) {
        this.payEvent = payEvent;
        this.payEventBroker = payEventBroker;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PaymentEvent> EventBroker<T> findByPayEvent(Class<T> payEvent) {
        return (EventBroker<T>) Arrays.stream(EventBrokerGroup.values())
                .filter(event -> event.getPayEvent().equals(payEvent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 EventBroker가 없습니다"))
                .getPayEventBroker();
    }

    private EventBroker<?> getPayEventBroker() {
        return payEventBroker;
    }

    public Class<? extends PaymentEvent> getPayEvent() {
        return payEvent;
    }
}
