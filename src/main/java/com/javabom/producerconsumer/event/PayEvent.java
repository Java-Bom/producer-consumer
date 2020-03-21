package com.javabom.producerconsumer.event;

import lombok.Getter;

@Getter
public class PayEvent implements Event {
    private Long amount;
    private String name;
    private PayType payType;

    @Override
    public String comma() {
        return String.format("%s,%s,%s",amount,name,payType);
    }
}
