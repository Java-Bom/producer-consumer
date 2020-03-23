package com.javabom.producerconsumer.event;

import lombok.Getter;

@Getter
public class CardPayEvent implements PayEvent {
    private Long amount;
    private String cardCompany;

    @Override
    public String comma() {
        return String.format("%d,%s", amount, cardCompany);
    }
}
