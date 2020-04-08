package com.javabom.producercomsumer.producercomsumer.pay.service.dto;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CardEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardEventRequestDto implements PaymentEventRequestDto {

    private String cardName;
    private int money;

    public CardEventRequestDto(String cardName, int money) {
        this.cardName = cardName;
        this.money = money;
    }

    @Override
    public PaymentEvent toEvent(Consumer<PaymentEvent> consumer) {
        return CardEvent.builder()
                .cardName(this.cardName)
                .money(this.money)
                .consumer(consumer)
                .build();
    }

    @Override
    public String toString() {
        return "CardPaymentEventRequestDto{" +
                "cardName='" + cardName + '\'' +
                ", money=" + money +
                '}';
    }
}
