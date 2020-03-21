package com.javabom.producerconsumer.domain;

import com.javabom.producerconsumer.event.PayType;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Pay {
    private Long amount;
    private String name;
    private PayType payType;

    @Builder
    private Pay(Long amount, String name, PayType payType) {
        this.amount = amount;
        this.name = name;
        this.payType = payType;
    }
}
