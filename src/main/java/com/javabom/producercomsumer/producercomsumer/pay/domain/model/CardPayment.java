package com.javabom.producercomsumer.producercomsumer.pay.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPayment implements Payment {

    protected int money;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
