package com.javabom.producercomsumer.producercomsumer.pay.service.dto;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PaymentEventRequestDto {
    protected int money;

    public PaymentEventRequestDto(int money) {
        this.money = money;
    }

    public abstract PaymentEvent toEvent(Consumer<PaymentEvent> consumer);
}
