package com.javabom.producercomsumer.producercomsumer.event;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class PayEventBroker {
    private final BlockingQueue<PayEvent> broker;

    public PayEventBroker(int capacity) {
        this.broker = new LinkedBlockingDeque<>(capacity);
    }

    public void push(PayEvent payEvent) {
        try {
            this.broker.add(payEvent);
        } catch (IllegalStateException e) {
            throw new RuntimeException("요청이 너무 많습니다.");
        }
    }

    public Optional<PayEvent> pop() {
        return Optional.ofNullable(this.broker.poll());
    }

    public Optional<PayEvent> peek() {
        return Optional.ofNullable(this.broker.peek());
    }

}
