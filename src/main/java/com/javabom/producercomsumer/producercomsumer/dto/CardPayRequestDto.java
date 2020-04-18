package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardPayRequestDto {
    private String userId;
    private int price;
    private String cardCompany;

    public CardPayRequestDto(String userId, int price, String cardCompany) {
        this.userId = userId;
        this.price = price;
        this.cardCompany = cardCompany;
    }

    @Override
    public String toString() {
        return "CardPaymentRequestDto{" +
                "userId='" + userId + '\'' +
                ", price=" + price +
                ", cardCompany='" + cardCompany + '\'' +
                '}';
    }
}
