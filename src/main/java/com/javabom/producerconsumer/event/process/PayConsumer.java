package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.PayEvent;
import com.javabom.producerconsumer.event.message.PayType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
// TODO: 2020. 3. 23. 동기화
public class PayConsumer {
    private final PayBrokerGroup payBrokerGroup;
    private final Map<Class<? extends PayEvent>, PayThreadPoolExecutor> threadPoolTaskExecutorMap = new HashMap<>();

    private boolean running = true;

    public PayConsumer(PayBrokerGroup payBrokerGroup) {
        this.payBrokerGroup = payBrokerGroup;
        for (Class<? extends PayEvent> eventClass : payBrokerGroup.keySet()) {
            Thread thread = new Thread(() -> consume(eventClass));
            thread.setName("thread_" + eventClass.getSimpleName());
            threadPoolTaskExecutorMap.put(eventClass, new PayThreadPoolExecutor(PayType.findBy(eventClass)));
            thread.start();
        }
    }

    private void consume(Class<? extends PayEvent> eventClass) {
        while (running) {
            Optional<? extends PayEvent> maybeEvent = payBrokerGroup.pop(eventClass);
            if (!maybeEvent.isPresent()) {
                continue;
            }
            threadPoolTaskExecutorMap.get(eventClass).executeJob(() -> pay(eventClass, maybeEvent.get()));
        }
    }

    private <E extends PayEvent> void pay(Class<E> eventClass, PayEvent payEvent) {
        try {
            payEvent.consume();
        } catch (Exception e) {
            log.warn(e.getMessage());
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
