package com.javabom.producercomsumer.producercomsumer.web.dto;

import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CashPayRequestDto {
    private String name;
    private long amount;

    public CashPayEvent toEvent() {
        return CashPayEvent.builder()
                .name(name)
                .amount(amount)
                .build();
    }
}
