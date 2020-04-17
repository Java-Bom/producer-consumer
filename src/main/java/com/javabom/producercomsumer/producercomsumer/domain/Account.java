package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private List<CardPayRecordEntity> cardPaymentHistories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private List<CashPayRecordEntity> cashPaymentHistories = new ArrayList<>();

    public Account(String userId) {
        this.userId = userId;
    }

    public void cardPay(CardPaymentRequestDto cardPaymentRequestDto, boolean complete) {
        log.info("CardPay: {}, {}", cardPaymentRequestDto.getCardCompany(), cardPaymentRequestDto.getPrice());
        this.cardPaymentHistories.add(new CardPayRecordEntity(cardPaymentRequestDto, complete));
    }

    public void cashPay(CashPaymentRequestDto cashPaymentRequestDto, boolean complete) {
        log.info("CashPay: {}, {}", cashPaymentRequestDto.getProductName(), cashPaymentRequestDto.getPrice());
        this.cashPaymentHistories.add(new CashPayRecordEntity(cashPaymentRequestDto, complete));
    }

    public void clearCardPayHistories() {
        cardPaymentHistories.clear();
    }

    public void clearCashPayHistories() {
        cashPaymentHistories.clear();
    }
}
