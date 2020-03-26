package com.javabom.producercomsumer.producercomsumer.service;

public interface PaymentService<E> {
    void requestPay(E requestDto);

    void pay(E requestDto);
}
