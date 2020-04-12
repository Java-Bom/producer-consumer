package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class CardPayment extends Payment {

    private String cardName;

    protected CardPayment() {
        super();
    }

    @Builder
    public CardPayment(String cardName, int money) {
        super(money);
        this.cardName = cardName;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardName='" + cardName + '\'' +
                '}';
    }
}

