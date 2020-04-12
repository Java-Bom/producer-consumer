package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.function.Consumer;


@Slf4j
public class BankConsumer<T extends PaymentEvent> {

    private final EventBroker<T> eventBroker;
    private final Consumer<T> consumer;

    public BankConsumer(EventBroker<T> eventBroker, Consumer<T> consumer, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.eventBroker = eventBroker;
        this.consumer = consumer;
        threadPoolTaskExecutor.execute(this::consume); // 얘를 읽고 실행하는 하나의 스레드가 필요하다. 읽는건 하나, 수행하는건 여러개
    }

    // 스레드는 하나만 쓰고있다.
    private void consume() {
        while (true) {
            try {
                log.info("Start: {}", Thread.currentThread().getName());
                T paymentEvent = this.eventBroker.poll();
                this.consumer.accept(paymentEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
