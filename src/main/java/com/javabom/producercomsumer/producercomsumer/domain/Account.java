package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
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

    public void cardPay(CardPayRequestDto cardPayRequestDto, boolean complete) {
        log.info("CardPay: {}, {}", cardPayRequestDto.getCardCompany(), cardPayRequestDto.getPrice());
        this.cardPaymentHistories.add(new CardPayRecordEntity(cardPayRequestDto, complete));
    }

    public void cashPay(CashPayRequestDto cashPayRequestDto, boolean complete) {
        log.info("CashPay: {}, {}", cashPayRequestDto.getProductName(), cashPayRequestDto.getPrice());
        this.cashPaymentHistories.add(new CashPayRecordEntity(cashPayRequestDto, complete));
    }

    public void clearCardPayHistories() {
        cardPaymentHistories.clear();
    }

    public void clearCashPayHistories() {
        cashPaymentHistories.clear();
    }
}
