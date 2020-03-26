package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPaymentRequestDto {
    private String userId;
    private String productName;
    private int price;

    public CashPaymentRequestDto(String userId, String productName, int price) {
        this.userId = userId;
        this.productName = productName;
        this.price = price;
    }
}
