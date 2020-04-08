package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class CardEvent implements PaymentEvent {
    private final String cardName;
    private final int money;
    private final Consumer<PaymentEvent> consumer;

    @Builder
    public CardEvent(String cardName, int money, Consumer<PaymentEvent> consumer) {
        this.cardName = cardName;
        this.money = money;
        this.consumer = consumer;
    }

    @Override
    public Payment toEntity() {
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
