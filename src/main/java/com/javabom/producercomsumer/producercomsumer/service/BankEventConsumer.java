package com.javabom.producercomsumer.producercomsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@RequiredArgsConstructor
public class BankEventConsumer<E> {

    private final Broker<E> broker;

    public void consumeEvent(){
        E e = broker.consumeEvent();

    }

}
