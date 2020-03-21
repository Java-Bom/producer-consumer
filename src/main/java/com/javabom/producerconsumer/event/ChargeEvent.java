package com.javabom.producerconsumer.event;

import lombok.Getter;

@Getter
public class ChargeEvent implements Event {
    private Long amount;
    private String name;
    private String description;

    @Override
    public String comma() {
        return String.format("%s,%s,%s",amount,name,description);
    }
}
