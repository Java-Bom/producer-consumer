package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPaymentRequestDto {

    private String name;
    private int price;

    @Builder
    public CashPaymentRequestDto(@NotBlank String name, int price) {
        this.name = name;
        this.price = price;
    }


}
