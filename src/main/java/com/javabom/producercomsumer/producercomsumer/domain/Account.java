package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID")
    private List<CardPaymentRecordEntity> cardPaymentHistories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID")
    private List<CashPaymentRecordEntity> cashPaymentHistories = new ArrayList<>();

    public Consumer<CardPaymentRequestDto> cardPay() {
        return (cardPaymentRequestDto) -> {
            log.info("CardPay: {}, {}", cardPaymentRequestDto.getCardCompany(), cardPaymentRequestDto.getPrice());
            this.cardPaymentHistories.add(new CardPaymentRecordEntity(cardPaymentRequestDto));
        };
    }

    public Consumer<CashPaymentRequestDto> cashPay() {
        return (cashPaymentRequestDto) -> {
            log.info("CardPay: {}, {}", cashPaymentRequestDto.getProductName(), cashPaymentRequestDto.getPrice());
            this.cashPaymentHistories.add(new CashPaymentRecordEntity(cashPaymentRequestDto));
        };
    }

}
