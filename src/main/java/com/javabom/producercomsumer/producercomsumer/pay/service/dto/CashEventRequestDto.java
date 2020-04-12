package com.javabom.producercomsumer.producercomsumer.pay.service.dto;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashEventRequestDto {
    private int money;
    private String name;

    public CashEventRequestDto(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public PaymentEvent toEvent(Consumer<CashEvent> consumer) {
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
