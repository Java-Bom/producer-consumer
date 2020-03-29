package com.javabom.producercomsumer.producercomsumer.pay.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargeEvent implements Event {

    private String name;
    private int money;
    private String description;

    public ChargeEvent(String name, int money, String description) {
        this.name = name;
        this.money = money;
        this.description = description;
    }

    @Override
    public void comma() {
        System.out.println(name + "," + money + "," + description);
    }
}
