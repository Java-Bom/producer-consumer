package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.event.PayEvent;

public interface PaymentService<E, T extends PayEvent> {
    void requestPay(E payRequestDto);

    void pay(T paymentEvent);
}
