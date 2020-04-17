package com.javabom.producercomsumer.producercomsumer.event;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class FailureEventBroker<T extends PayEvent> {
    private static final int LIMIT_QUEUE_SIZE = 30;
    private Queue<T> failureEventQueue = new LinkedBlockingQueue<>(); // 이 Queue에 접근하는 스레드는 하나이거나 동기화.

    public void offer(T payEvent) {
        if (failureEventQueue.size() > LIMIT_QUEUE_SIZE) {
            throw new IllegalArgumentException("더이상 요청할 수 없습니다");
        }
        log.info("offer {} Event in EventBroker", payEvent.getClass().getName());
        failureEventQueue.offer(payEvent);
    }

    public T poll() throws InterruptedException {
        while (failureEventQueue.size() <= 0) {
            Thread.sleep(3000);
        }
        return failureEventQueue.poll();
    }
}
