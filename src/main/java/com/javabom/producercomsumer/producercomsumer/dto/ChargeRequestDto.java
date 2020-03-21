package com.javabom.producercomsumer.producercomsumer.dto;

import com.javabom.producercomsumer.producercomsumer.domain.Bank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ChargeRequestDto {
    private int price;
    private String description;

    @Builder
    public ChargeRequestDto(int price, String description) {
        this.price = price;
        this.description = description;
    }

    public String comma() {
        return "ChargeRequestDto";
    }

    public Bank toEntity() {
        return new Bank();
    }
}
