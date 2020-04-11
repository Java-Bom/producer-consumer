package com.javabom.producerconsumer.web.dto;

import com.javabom.producerconsumer.event.message.CashPayEvent;
import com.javabom.producerconsumer.event.message.PayEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public class CashPayRequest {
    private Long amount;
    private String name;

    public CashPayEvent toEvent(Consumer<CashPayEvent> consumer, Consumer<PayEvent> failRequestConsumer) {
        return CashPayEvent.builder()
                .amount(amount)
                .name(name)
                .consumer(consumer)
                .failConsumer(failRequestConsumer)
                .build();
    }
}
