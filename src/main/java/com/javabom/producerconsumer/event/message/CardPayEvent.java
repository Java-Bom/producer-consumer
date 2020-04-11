package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class CardPayEvent extends PayEvent {
    private static final int MAX_TRY_COUNT = 3;
    private Long amount;
    private String cardCompany;
    private Consumer<CardPayEvent> consumer;

    @Builder
    private CardPayEvent(Long amount, String cardCompany, Consumer<CardPayEvent> consumer, Consumer<PayEvent> failConsumer) {
        super(failConsumer);
        this.amount = amount;
        this.cardCompany = cardCompany;
        this.consumer = consumer;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, cardCompany);
    }

    @Override
    public FailRequest toFail() {
        return new FailRequest(PayType.CARD);
    }

    @Override
    protected void pay() {
        consumer.accept(this);
    }
}
