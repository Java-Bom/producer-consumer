package com.javabom.producerconsumer.event;

import java.util.Optional;
import java.util.function.Consumer;


// TODO: 2020. 3. 23. 동기화
public class PayConsumer<E extends PayEvent> {
    private final Class<E> eventType;
    private final Consumer<E> consumer;
    private boolean running = true;

    public PayConsumer(Class<E> eventType, Consumer<E> consumer) {
        this.eventType = eventType;
        this.consumer = consumer;
        new Thread(this::consume).start();
    }

    public void consume() {
        while (running) {
            Optional<E> event = PayBrokerGroup.pop(eventType);
            event.ifPresent(consumer);
        }
    }

    public void stop() {
        this.running = false;
    }

}
