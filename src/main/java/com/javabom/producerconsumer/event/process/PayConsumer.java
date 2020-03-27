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
            Optional<? extends PayEvent> event = payBrokerGroup.pop(eventClass);
            event.ifPresent(PayEvent::consume);
        }
    }

    public void stop() {
        this.running = false;
    }

}
