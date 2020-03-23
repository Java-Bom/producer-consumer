package com.javabom.producerconsumer.event;

import java.util.Optional;
import java.util.function.Consumer;


// TODO: 2020. 3. 23. 동기화
public class PayConsumer<E extends PayEvent> {
    private final PayRequestBroker<E> broker;
    private final Consumer<E> consumer;
    private boolean running = true;

    public PayConsumer(PayRequestBroker<E> broker, Consumer<E> consumer) {
        this.broker = broker;
        this.consumer = consumer;
        new Thread(this::consume).start();
    }

    public void consume() {
        while (running) {
            Optional<E> event = broker.pop();
            event.ifPresent(consumer);
        }
    }

    public void stop() {
        this.running = false;
    }

}
