package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class PaymentEventBroker<E extends Payment> {
    private final Queue<E> eventQueue = new LinkedList<>();

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public void add(E event) {
        if (explode(100)) {
            log.error("100개 이상 이벤트를 보관할 수 없습니다. 실패한 이벤트 : {}", event.toString());
            return;
        }
        eventQueue.add(event);
    }

    public E poll() {
        return eventQueue.poll();
    }

    public boolean explode(int limit) {
        return eventQueue.size() > limit;
    }
}
