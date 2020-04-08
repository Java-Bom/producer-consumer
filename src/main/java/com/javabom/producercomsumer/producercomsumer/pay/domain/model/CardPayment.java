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
public class CardPayment extends Payment {

    private String cardName;
    private int money;

    public CardPayment(String cardName, int money) {
        this.cardName = cardName;
        this.money = money;
    }

    @Override
    public void comma() {
        System.out.println(cardName + "," + money);
    }
}
