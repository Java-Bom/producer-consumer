package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPayment extends Payment {

    private String cardName;

    @Builder
    public CardPayment(String cardName, int money) {
        this.cardName = cardName;
        this.money = money;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardName='" + cardName + '\'' +
                ", money=" + money +
                '}';
    }
}
