package com.javabom.producerconsumer.event.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum PayType {
    CASH(CashPayEvent.class, 1, 1, 1),
    CARD(CardPayEvent.class, 1, 1, 1);

    private final Class<? extends PayEvent> eventClass;
    private final int corePoolSize;
    private final int maxPoolSize;
    private final int queueCapacity;


    public static List<Class<? extends PayEvent>> getEventClasses() {
        return Arrays.stream(PayType.values())
                .map(PayType::getEventClass)
                .collect(Collectors.toList());
    }

    public static PayType findBy(Class<? extends PayEvent> eventClass) {
        return Arrays.stream(PayType.values())
                .filter(type -> type.getEventClass() == eventClass)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
