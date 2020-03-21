package com.javabom.producerconsumer.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class RequestBroker<E extends Event> {
    private final Queue<E> broker;

    public void push(E e) {
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
