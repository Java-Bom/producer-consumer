package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;

public interface PaymentService<E, T extends PaymentEvent> {
    void requestPay(E payRequestDto);

    void pay(T paymentEvent);
}
