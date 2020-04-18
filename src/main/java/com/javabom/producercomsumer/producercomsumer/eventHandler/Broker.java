package com.javabom.producercomsumer.producercomsumer.eventHandler;

import com.javabom.producercomsumer.producercomsumer.event.BankEvent;
import com.javabom.producercomsumer.producercomsumer.exception.EventFailException;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
public class Broker<E extends BankEvent<?>> {

    private Queue<E> bankEventQueue;
    private static final int MAX_EVENT_SIZE = 2;

    public Broker() {
        this.bankEventQueue = new LinkedList<>();
    }

    public void registerEvent(E javaBomBank) {
        if(bankEventQueue.size() >= MAX_EVENT_SIZE){
            throw new EventFailException(javaBomBank.failEventMessage());
        }
        bankEventQueue.add(javaBomBank);
    }

    public E pollEvent() {
        log.info(getRetainEvents());
        return bankEventQueue.poll();
    }

    public boolean hasConsumeEvent() {
        return !bankEventQueue.isEmpty();
    }

    private String getRetainEvents() {
        String queueContents = bankEventQueue.stream()
                .map(c -> c.getEventContents())
                .collect(Collectors.joining(","));

        return String.format("retain events : %s", queueContents);
    }

}
