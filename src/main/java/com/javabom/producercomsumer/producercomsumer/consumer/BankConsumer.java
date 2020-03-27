package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;


@Slf4j
public class BankConsumer<T extends PaymentEvent> {

    private final EventBroker<T> eventBroker;
    private final Consumer<T> consumer;

    public BankConsumer(EventBroker<T> eventBroker, Consumer<T> consumer) {
        this.eventBroker = eventBroker;
        this.consumer = consumer;
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    PaymentEvent paymentEvent = this.eventBroker.poll();
                    this.consumer.accept((T) paymentEvent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Math.random() + "번 스레드");
        thread.start();

    }
}
