package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public class CashEvent implements PaymentEvent {
    private final String name;
    private final int money;
    private final Consumer<CashEvent> consumer;

    @Builder
    public CashEvent(String name, int money, Consumer<CashEvent> consumer) {
        this.name = name;
        this.money = money;
        this.consumer = consumer;
    }

    @Override
    public CashPayment toEntity() {
        return CashPayment.builder()
                .name(this.name)
                .money(this.money)
                .build();
    }

    @Override
    public void consume() {
        this.consumer.accept(this);
    }
}
