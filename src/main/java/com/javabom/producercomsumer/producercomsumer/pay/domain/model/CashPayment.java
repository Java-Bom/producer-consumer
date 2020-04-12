package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class CashPayment extends Payment {

    private String name;

    protected CashPayment() {
        super();
    }

    @Builder
    public CashPayment(String name, int money) {
        super(money);
        this.name = name;
    }

    @Override
    public String toString() {
        return "CashPayment{" +
                "name='" + name + '\'' +
                '}';
    }
}
