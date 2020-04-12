package com.javabom.producercomsumer.producercomsumer.event;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class EventBroker<T extends PaymentEvent> {
    private static final int LIMIT_QUEUE_SIZE = 100;
    private Queue<T> eventQueue = new LinkedBlockingQueue<>(); // Event 유실, 동시에 같은 이벤트를 꺼낼 수 있기 떄문에 Blocking

    public void offer(T javabomEvent) {
        if (eventQueue.size() > LIMIT_QUEUE_SIZE) {
            throw new IllegalArgumentException("더이상 요청할 수 없습니다");
        }
        log.info("offer {} Event in EventBroker", javabomEvent.getClass().getName());
        eventQueue.offer(javabomEvent);
    }

    public T poll() throws InterruptedException {
        while (eventQueue.size() <= 0) {
            Thread.sleep(3000);
        }
        return eventQueue.poll();
    }
}
