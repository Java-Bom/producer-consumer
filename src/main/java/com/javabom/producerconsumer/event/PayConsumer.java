package com.javabom.producerconsumer.event;

import java.util.Optional;
import java.util.function.Consumer;

public class PayConsumer<E extends PayEvent> {
    private final PayRequestBroker<E> broker;
    private final Consumer<E> consumer;

    public PayConsumer(PayRequestBroker<E> broker, Consumer<E> consumer) {
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
