package com.javabom.producerconsumer.domain;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Charge {
    private Long amount;
    private String name;
    private String description;

    @Builder
    private Charge(Long amount, String name, String description) {
        this.amount = amount;
        this.name = name;
        this.description = description;
    }
}
