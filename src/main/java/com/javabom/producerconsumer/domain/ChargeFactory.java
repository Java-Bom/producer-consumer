package com.javabom.producerconsumer.domain;

import com.javabom.producerconsumer.event.ChargeEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChargeFactory {

    public static void charge(ChargeEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getName();
        final String description = event.getDescription();

        Charge charge = Charge.builder()
                .amount(amount)
                .description(description)
                .name(name)
                .build();

        log.info(charge.toString());
    }
}
