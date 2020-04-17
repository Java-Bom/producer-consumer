package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.FailureEventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.function.Consumer;

@Slf4j
public class PayFailureConsumer<T extends PayEvent> {
    private final FailureEventBroker<T> eventBroker;
    private final Consumer<T> consumer;
    private final ThreadPoolTaskExecutor threadPoolExecutor;

    public PayFailureConsumer(FailureEventBroker<T> eventBroker, Consumer<T> consumer, ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.eventBroker = eventBroker;
        this.consumer = consumer;
        this.threadPoolExecutor = threadPoolTaskExecutor;
        new Thread(this::pollEvent).start();
        // threadPoolTaskExecutor.execute(this::consume); // 얘를 읽고 실행하는 하나의 스레드가 필요하다. 읽는건 하나, 수행하는건 여러개
    }

    private void pollEvent() {
        while (true) {
            log.info("Pick Event: {}", Thread.currentThread().getName());
            try {
                T payEvent = this.eventBroker.poll(); // 하나의 스레드가 이벤트를 계속 꺼낸다.
                threadPoolExecutor.execute(() -> consume(payEvent)); // 그리고 스레드풀에서 스레드를 꺼내 소모하도록 한다.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume(T payEvent) {
        log.info("실패이벤트를 소모합니다: {}", Thread.currentThread().getName());
        this.consumer.accept(payEvent);
    }

}
