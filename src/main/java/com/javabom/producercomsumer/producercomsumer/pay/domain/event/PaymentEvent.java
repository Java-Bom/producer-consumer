package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;

public interface PaymentEvent {
    Payment toEntity();

    void consume();
}
