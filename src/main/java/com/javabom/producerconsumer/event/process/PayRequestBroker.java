package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.PayEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class PayRequestBroker<E extends PayEvent> {
    private final BlockingQueue<E> broker;

    public PayRequestBroker(int capacity) {
        this.broker = new LinkedBlockingQueue<>(capacity);
    }

    public void push(E e) {
        broker.add(e);
    }

    public Optional<E> pop() {
        E event = broker.poll();
        if (event != null) {
            log.info(event.comma());
        }

        return Optional.ofNullable(event);
    }

}
