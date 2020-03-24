package com.javabom.producerconsumer.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashPayHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private String name;

    @Builder
    private CashPayHistory(Long amount, String name) {
        this.amount = amount;
        this.name = name;
    }
}
