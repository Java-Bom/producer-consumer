package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class CardEvent implements PaymentEvent {
    private final String cardName;
    private final int money;
    private final Consumer<CardEvent> consumer;

    @Builder
    public CardEvent(String cardName, int money, Consumer<CardEvent> consumer) {
        this.cardName = cardName;
        this.money = money;
        this.consumer = consumer;
    }

    @Override
    public CardPayment toEntity() {
        return CardPayment.builder()
                .cardName(this.cardName)
                .money(this.money)
                .build();
    }

    @Override
    public void consume() {
        this.consumer.accept(this);
    }
}
