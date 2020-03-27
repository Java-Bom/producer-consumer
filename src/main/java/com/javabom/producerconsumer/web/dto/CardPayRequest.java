package com.javabom.producerconsumer.web.dto;

import com.javabom.producerconsumer.event.message.CardPayEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public class CardPayRequest {
    private Long amount;
    private String cardCompany;

    public CardPayEvent toEvent(Consumer<CardPayEvent> consumer) {
        return CardPayEvent.builder()
                .amount(amount)
                .cardCompany(cardCompany)
                .consumer(consumer)
                .build();
    }
}
