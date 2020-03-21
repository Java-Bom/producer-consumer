package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class Bank {

    public Consumer<String> pay(final int price) {
        return (str) -> log.info("Bank - payMethod:  {}", str);
    }

    public Consumer<String> charge(final int price) {
        return (str) -> log.info("Bank - chargeMethod:    {}", str);
    }

}
