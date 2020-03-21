package com.javabom.producercomsumer.producercomsumer.dto;

import com.javabom.producercomsumer.producercomsumer.domain.Bank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayRequestDto {

    private PayType payType;
    private int price;

    @Builder
    public PayRequestDto(@NotBlank PayType payType, int price) {
        this.payType = payType;
        this.price = price;
    }

    public Bank toEntity() {
        return new Bank();
    }

}
