package com.javabom.producercomsumer.producercomsumer.pay.service.dto;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashEventRequestDto implements PaymentEventRequestDto {
    private String name;
    private int money;

    public CashEventRequestDto(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public PaymentEvent toEvent(Consumer<PaymentEvent> consumer) {
        return CashEvent.builder()
                .name(this.name)
                .money(this.money)
                .consumer(consumer)
                .build();
    }

    @Override
    public String toString() {
        return "CashPaymentEventRequestDto{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
