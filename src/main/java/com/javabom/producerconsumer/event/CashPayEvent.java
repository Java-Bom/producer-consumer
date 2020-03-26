package com.javabom.producerconsumer.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CashPayEvent implements PayEvent {
    private Long amount;
    private String name;


    @Builder(builderMethodName = "testBuilder")
    private CashPayEvent(Long amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, name);
    }
}
