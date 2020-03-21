package com.javabom.producercomsumer.producercomsumer.dto;

public enum PayType {
    CARD("카드"), CACHE("현금");

    private String name;

    PayType(String name) {
        this.name = name;
    }

}