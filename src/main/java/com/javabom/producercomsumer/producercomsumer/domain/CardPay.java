package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CardPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardCompany;
    private Long amount;

    @Builder
    public CardPay(String cardCompany, Long amount) {
        this.cardCompany = cardCompany;
        this.amount = amount;
    }
}
