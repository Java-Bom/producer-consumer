package com.javabom.producercomsumer.producercomsumer.event;

import java.util.Optional;

public class PayEventConsumer {
    private final PayEventBroker eventBroker;

    public PayEventConsumer(PayEventBroker eventBroker) {
        this.eventBroker = eventBroker;
        for (PayEventType payEventType : PayEventType.values()) {
            Thread thread = new Thread(() -> consume(payEventType.getPayEventType()));
            thread.setName("Thread - " + payEventType.name());
            thread.start();
        }

    }

    private void consume(Class<?> eventType) {
        while (true) {
            Optional<PayEvent> payEvent = this.eventBroker.peek();

            if (!payEvent.isPresent()) {
                continue;
            }

            if (payEvent.get().getClass() != eventType) {
                continue;
            }

            this.eventBroker.pop().get().consume();
        }
    }
}