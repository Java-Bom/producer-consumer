package com.javabom.producerconsumer.event.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum PayType {
    CASH(CashPayEvent.class),
    CARD(CardPayEvent.class);

    private final Class<? extends PayEvent> eventClass;


    public static List<Class<? extends PayEvent>> getEventClasses() {
        return Arrays.stream(PayType.values())
                .map(PayType::getEventClass)
                .collect(Collectors.toList());
    }
}
