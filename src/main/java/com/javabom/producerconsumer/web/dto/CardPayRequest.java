package com.javabom.producerconsumer.web.dto;

import com.javabom.producerconsumer.event.message.CardPayEvent;
import com.javabom.producerconsumer.event.message.PayEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public class CardPayRequest {
    private Long amount;
    private String cardCompany;

    public CardPayEvent toEvent(Consumer<CardPayEvent> consumer, Consumer<PayEvent> failConsumer) {
        return CardPayEvent.builder()
                .amount(amount)
                .cardCompany(cardCompany)
                .consumer(consumer)
                .failConsumer(failConsumer)
                .build();
    }
}
