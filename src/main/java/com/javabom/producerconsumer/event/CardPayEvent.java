package com.javabom.producerconsumer.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardPayEvent implements PayEvent {
    private Long amount;
    private String cardCompany;

    @Builder(builderMethodName = "testBuilder")
    private CardPayEvent(Long amount, String cardCompany) {
        this.amount = amount;
        this.cardCompany = cardCompany;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, cardCompany);
    }
}
