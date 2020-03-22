package com.javabom.producercomsumer.producercomsumer.dto;

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

}
