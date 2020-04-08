package com.javabom.producercomsumer.producercomsumer.pay.service.dto;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;

import java.util.function.Consumer;

public interface PaymentEventRequestDto {
    PaymentEvent toEvent(Consumer<PaymentEvent> consumer);
}
