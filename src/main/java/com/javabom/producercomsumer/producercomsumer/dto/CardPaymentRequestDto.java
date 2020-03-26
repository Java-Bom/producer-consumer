package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CardPaymentRequestDto {
    private int price;
    private String cardCompany;

    @Builder
    public CardPaymentRequestDto(int price, String cardCompany) {
        this.price = price;
        this.cardCompany = cardCompany;
    }

}
