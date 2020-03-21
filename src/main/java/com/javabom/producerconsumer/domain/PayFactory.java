package com.javabom.producerconsumer.domain;

import com.javabom.producerconsumer.event.PayEvent;
import com.javabom.producerconsumer.event.PayType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFactory {

    public static void pay(PayEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getName();
        final PayType type = event.getPayType();

        Pay pay = Pay.builder()
                .amount(amount)
                .payType(type)
                .name(name)
                .build();

        log.info(pay.toString());
    }
}
