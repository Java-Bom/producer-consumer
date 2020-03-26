package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CardPaymentRequestDto {
    private String userId;
    private int price;
    private String cardCompany;

    public CardPaymentRequestDto(String userId, int price, String cardCompany) {
        this.userId = userId;
        this.price = price;
        this.cardCompany = cardCompany;
    }
}
