package com.javabom.producerconsumer.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CashPayEvent implements PayEvent {
    private Long amount;
    private String name;

    @Override
    public String comma() {
        return String.format("%d,%s", amount, name);
    }
}
