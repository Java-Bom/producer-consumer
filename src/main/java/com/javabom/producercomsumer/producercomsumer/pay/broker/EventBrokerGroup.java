package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.Event;
import com.javabom.producercomsumer.producercomsumer.pay.domain.PayEvent;

import java.util.Arrays;

public enum EventBrokerGroup {
    PAY(new EventBroker<PayEvent>(), PayEvent.class),
    CHARGE(new EventBroker<ChargeEvent>(), ChargeEvent.class);

    private final EventBroker<? extends Event> eventBroker;
    private final Class<? extends Event> eventClass;

    EventBrokerGroup(EventBroker<? extends Event> eventBroker, Class<? extends Event> eventClass) {
        this.eventBroker = eventBroker;
        this.eventClass = eventClass;
    }

    @SuppressWarnings("unchecked")
    public static <E extends Event> EventBroker<E> findByEvent(E event) {
        return (EventBroker<E>) Arrays.stream(values())
                .filter(brokerGroup -> brokerGroup.eventClass.equals(event.getClass()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .eventBroker;
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> EventBroker<E> getEventBroker() {
        return (EventBroker<E>) this.eventBroker;
    }

}
