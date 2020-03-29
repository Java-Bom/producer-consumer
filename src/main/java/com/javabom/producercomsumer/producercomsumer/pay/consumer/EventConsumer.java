package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.EventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.domain.Event;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class EventConsumer<E extends Event> implements Runnable {

    private final EventBroker<E> eventBroker;
    private final Consumer<E> eventConsumer;

    public EventConsumer(EventBroker<E> eventBroker, Consumer<E> eventConsumer) {
        this.eventBroker = eventBroker;
        this.eventConsumer = eventConsumer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (eventBroker.isEmpty()) {
                continue;
            }
            eventConsumer.accept(eventBroker.poll());
        }
    }
}
