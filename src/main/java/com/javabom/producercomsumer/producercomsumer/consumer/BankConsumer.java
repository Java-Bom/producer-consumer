package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.function.Consumer;


@Slf4j
public class BankConsumer<T extends PayEvent> {

    private final EventBroker<T> eventBroker;
    private final Consumer<T> consumer;
    private final ThreadPoolTaskExecutor threadPoolExecutor;

    public BankConsumer(EventBroker<T> eventBroker, Consumer<T> consumer, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.eventBroker = eventBroker;
        this.consumer = consumer;
        this.threadPoolExecutor = threadPoolTaskExecutor;
        new Thread(this::pollEvent).start();
    }

    private void pollEvent() {
        while (true) {
            log.info("Pick Event: {}", Thread.currentThread().getName());
            try {
                T payEvent = this.eventBroker.poll();
                threadPoolExecutor.execute(() -> consume(payEvent));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume(T payEvent) {
        log.info("Consume Event: {}", Thread.currentThread().getName());
        this.consumer.accept(payEvent);
    }
}
