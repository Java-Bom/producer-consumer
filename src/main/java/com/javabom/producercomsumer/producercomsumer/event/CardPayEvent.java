package com.javabom.producercomsumer.producercomsumer.event;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Getter
@Slf4j
public class CardPayEvent implements PayEvent {
    private String cardCompany;
    private long amount;
    private Consumer<CardPayEvent> consumer;

    public CardPayEvent(String cardCompany, long amount, Consumer<CardPayEvent> consumer) {
        this.cardCompany = cardCompany;
        this.amount = amount;
        this.consumer = consumer;
    }

    @Builder
    public CardPayEvent(String cardCompany, long amount) {
        this.cardCompany = cardCompany;
        this.amount = amount;
    }

    @Override
    public void consume() {
        log.info(Thread.currentThread().getName() + " : " +
                "CardEvent : company - {}, amount - {}", cardCompany, amount);
    }
}
