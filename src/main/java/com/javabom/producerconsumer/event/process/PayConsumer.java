package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.PayEvent;

import java.util.Optional;


// TODO: 2020. 3. 23. 동기화
public class PayConsumer {
    private final PayBrokerGroup payBrokerGroup;

    private boolean running = true;

    public PayConsumer(PayBrokerGroup payBrokerGroup) {
        this.payBrokerGroup = payBrokerGroup;
        for (Class<? extends PayEvent> eventClass : payBrokerGroup.keySet()) {
            Thread thread = new Thread(() -> consume(eventClass));
            thread.setName("thread_" + eventClass.getSimpleName());
            thread.start();
        }
    }

    private void consume(Class<? extends PayEvent> eventClass) {
        while (running) {
            Optional<? extends PayEvent> maybeEvent = payBrokerGroup.pop(eventClass);
            if (!maybeEvent.isPresent()) {
                return;
            }

            pay(eventClass, maybeEvent.get());
        }
    }

    private <E extends PayEvent> void pay(Class<E> eventClass, PayEvent payEvent) {
        try {
            payEvent.consume();
        } catch (Exception e) {
            retry(eventClass, payEvent);
        }
    }

    private <E extends PayEvent> void retry(Class<E> eventClass, PayEvent payEvent) {
        if (payEvent.isEnableRetry()) {
            payBrokerGroup.put(eventClass, eventClass.cast(payEvent));
            return;
        }
        payEvent.consumeFail();
    }

    public void stop() {
        this.running = false;
    }
}
