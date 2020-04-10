package com.javabom.producercomsumer.producercomsumer.web.dto;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardPayRequestDto {
    private String cardCompany;
    private Long amount;

    public CardPayEvent toEvent() {
        return CardPayEvent.builder()
                .amount(amount)
                .cardCompany(cardCompany)
                .build();
    }
}
