package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.PayEvent;
import com.javabom.producerconsumer.event.message.PayType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PayBrokerGroup {
    public static final int EVENT_CAPACITY = 100;

    private final Map<Class<? extends PayEvent>, PayRequestBroker<? extends PayEvent>> brokerMap = new HashMap<>();

    public PayBrokerGroup() {
        for (Class<? extends PayEvent> eventClass : PayType.getEventClasses()) {
            brokerMap.put(eventClass, new PayRequestBroker<>(EVENT_CAPACITY));
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends PayEvent> void put(Class<E> type, E event) {
        PayRequestBroker<E> broker = (PayRequestBroker<E>) brokerMap.get(type);
        broker.push(type.cast(event));
    }

    @SuppressWarnings("unchecked")
    public <E extends PayEvent> Optional<E> pop(Class<E> type) {
        PayRequestBroker<E> broker = (PayRequestBroker<E>) brokerMap.get(type);
        return broker.pop();
    }

    public Set<Class<? extends PayEvent>> keySet() {
        return brokerMap.keySet();
    }
}
