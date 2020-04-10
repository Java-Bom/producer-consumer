package com.javabom.producercomsumer.producercomsumer.event;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Getter
@Slf4j
public class CashPayEvent implements PayEvent {
    private String name;
    private long amount;
    private Consumer<CashPayEvent> consumer;

    public CashPayEvent(String name, long amount, Consumer<CashPayEvent> consumer) {
        this.name = name;
        this.amount = amount;
        this.consumer = consumer;
    }

    @Builder
    public CashPayEvent(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public void consume() {
        log.info(Thread.currentThread().getName() + " : " +
                "CashEvent : name - {}, amount - {}", name, amount);
    }
}
