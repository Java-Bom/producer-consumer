package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public class CashPayEvent extends PayEvent {
    private Long amount;
    private String name;
    private Consumer<CashPayEvent> consumer;

    @Builder
    private CashPayEvent(Long amount, String name, Consumer<CashPayEvent> consumer) {
        this.amount = amount;
        this.name = name;
        this.consumer = consumer;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, name);
    }

    @Override
    public void consume() {
        consumer.accept(this);
    }

    @Override
    public FailRequest toFail() {
        return new FailRequest(PayType.CASH);
    }
}
