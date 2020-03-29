package com.javabom.producercomsumer.producercomsumer.pay.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayEvent implements Event {
    private String name;
    private int money;
    private PayType payType;

    public PayEvent(String name, int money, PayType payType) {
        this.name = name;
        this.money = money;
        this.payType = payType;
    }

    @Override
    public void comma() {
        System.out.println(name + "," + money + "," + payType);
    }

    enum PayType {CASH, CARD;}
}
