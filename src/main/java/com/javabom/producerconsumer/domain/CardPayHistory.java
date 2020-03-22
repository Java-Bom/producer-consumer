package com.javabom.producerconsumer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CardPayHistory {
    private Long amount;
    private String cardCompany;

    @Builder
    private CardPayHistory(Long amount, String cardCompany) {
        this.amount = amount;
        this.cardCompany = cardCompany;

    }
}
