package com.javabom.producercomsumer.producercomsumer.event;

import java.util.Arrays;

public enum EventBrokerGroup {
    CARD(CardPayEvent.class, new EventBroker<CardPayEvent>(), new FailureEventBroker<CardPayEvent>()),
    CASH(CashPayEvent.class, new EventBroker<CashPayEvent>(), new FailureEventBroker<CardPayEvent>());

    private Class<? extends PayEvent> payEvent;
    private EventBroker<? extends PayEvent> payEventBroker;
    private FailureEventBroker<? extends PayEvent> failureEventBroker;

    EventBrokerGroup(Class<? extends PayEvent> payEvent, EventBroker<? extends PayEvent> payEventBroker, FailureEventBroker<? extends PayEvent> failureEventBroker) {
        this.payEvent = payEvent;
        this.payEventBroker = payEventBroker;
        this.failureEventBroker = failureEventBroker;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PayEvent> EventBroker<T> findPayEventBroker(Class<T> payEvent) {
        return (EventBroker<T>) Arrays.stream(values())
                .filter(event -> event.getPayEvent().equals(payEvent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 EventBroker가 없습니다"))
                .getPayEventBroker();
    }

    @SuppressWarnings("unchecked")
    public static <T extends PayEvent> FailureEventBroker<T> findFailureEventBroker(Class<T> payEvent) {
        return (FailureEventBroker<T>) Arrays.stream(values())
                .filter(event -> event.getPayEvent().equals(payEvent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 Event Broker가 없습니다"))
                .getFailureEventBroker();
    }

    private EventBroker<?> getPayEventBroker() {
        return payEventBroker;
    }

    private Class<? extends PayEvent> getPayEvent() {
        return payEvent;
    }

    private FailureEventBroker<? extends PayEvent> getFailureEventBroker() {
        return failureEventBroker;
    }
}
