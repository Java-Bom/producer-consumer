package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class PaymentEventBroker<E extends PaymentEvent> {
    private final BlockingQueue<E> eventQueue = new LinkedBlockingQueue<>();
    private final int limit;

    public PaymentEventBroker(int limit) {
        if (limit <= 1) {
            throw new IllegalArgumentException();
        }
        this.limit = limit;
    }

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public void add(E event) {
        if (explode()) {
            throw new IllegalStateException(String.format("100개 이상 이벤트를 보관할 수 없습니다. 실패한 이벤트 : %s", event.toString()));
        }
        eventQueue.add(event);
    }

    public E poll() {
        return eventQueue.poll();
    }

    private boolean explode() {
        return eventQueue.size() >= this.limit;
    }
}
