package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPayment extends Payment {
    private String name;
    private int money;

    @Builder
    public CashPayment(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return "CashPayment{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
