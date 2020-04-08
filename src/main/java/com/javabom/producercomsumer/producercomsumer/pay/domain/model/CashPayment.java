package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPayment extends Payment {
    private String name;
    private int money;

    public CashPayment(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public void comma() {
        System.out.println(name + "," + money);
    }
}
