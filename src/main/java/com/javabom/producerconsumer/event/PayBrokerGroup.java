package com.javabom.producerconsumer.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

import static com.javabom.producerconsumer.event.EventConfig.EVENT_CAPACITY;


// TODO: 2020. 3. 24. 제네릭 컨테이너 리펙토링
@Getter
@RequiredArgsConstructor
public enum PayBrokerGroup {
    CARD_BROKER(CardPayEvent.class, new PayRequestBroker<CardPayEvent>(EVENT_CAPACITY)),
    CASH_BROKER(CashPayEvent.class, new PayRequestBroker<CashPayEvent>(EVENT_CAPACITY));

    private final Class<? extends PayEvent> eventType;
    private final PayRequestBroker<? extends PayEvent> broker;

    public static <E extends PayEvent> void put(Class<E> type, E event) {
        PayBrokerGroup group = findBroker(type);
        PayRequestBroker<E> broker = (PayRequestBroker<E>) group.broker;
        broker.push(type.cast(event));
    }

    public static <E extends PayEvent> Optional<E> pop(Class<E> type) {
        PayBrokerGroup group = findBroker(type);
        PayRequestBroker<E> broker = (PayRequestBroker<E>) group.broker;
        return broker.pop();
    }

    private static PayBrokerGroup findBroker(Class<?> type) {
        return Arrays.stream(values())
                .filter(broker -> broker.eventType == type)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
