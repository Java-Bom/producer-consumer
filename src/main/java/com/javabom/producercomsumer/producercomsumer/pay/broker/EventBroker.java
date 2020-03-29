package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.Event;

import java.util.LinkedList;
import java.util.Queue;

public class EventBroker<E extends Event> {
    private final Queue<E> eventQueue = new LinkedList<>();

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public void add(E event) {
        eventQueue.add(event);
    }

    public E poll() {
        return eventQueue.poll();
    }
}
