package com.javabom.producerconsumer.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class PayRequestBroker<E extends PayEvent> {
    private final Queue<E> broker;

    public void push(E e) {
        if (broker.size() >= 100) {
            throw new RuntimeException("결제가 불가능합니다.");
        }
        broker.add(e);
    }

    public Optional<E> pop() {
        E event = broker.poll();
        if (event != null) {
            log.info(event.comma());
        }

        return Optional.ofNullable(event);
    }
}
