package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPayRequestDto {
    private String userId;
    private String productName;
    private int price;

    @Builder
    public CashPayRequestDto(String userId, String productName, int price) {
        this.userId = userId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CashPaymentRequestDto{" +
                "userId='" + userId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
