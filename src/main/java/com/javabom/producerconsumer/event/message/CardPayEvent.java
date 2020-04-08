package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public class CardPayEvent extends PayEvent {
    private Long amount;
    private String cardCompany;
    private Consumer<CardPayEvent> consumer;

    @Builder
    private CardPayEvent(Long amount, String cardCompany, Consumer<CardPayEvent> consumer) {
        this.amount = amount;
        this.cardCompany = cardCompany;
        this.consumer = consumer;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, cardCompany);
    }

    @Override
    public void consume() {
        consumer.accept(this);
    }

    @Override
    public FailRequest toFail() {
        return new FailRequest(PayType.CARD);
    }
}
