package com.javabom.producerconsumer.event;

import java.util.Optional;
import java.util.function.Consumer;

public class ConsumerComponent<E extends Event> {
    private final RequestBroker<E> broker;
    private final Consumer<E> consumer;

    public ConsumerComponent(RequestBroker<E> broker, Consumer<E> consumer) {
        this.broker = broker;
        this.consumer = consumer;
        new Thread(this::consume).start();
    }

    public void consume() {
        while (true) {
            Optional<E> event = broker.pop();
            event.ifPresent(consumer);
        }
    }
}
