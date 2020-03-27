package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.domain.CardPayHistory;
import com.javabom.producerconsumer.domain.CardPayHistoryRepository;
import com.javabom.producerconsumer.domain.CashPayHistory;
import com.javabom.producerconsumer.domain.CashPayHistoryRepository;
import com.javabom.producerconsumer.event.message.CardPayEvent;
import com.javabom.producerconsumer.event.message.CashPayEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final CardPayHistoryRepository cardPayHistoryRepository;
    private final CashPayHistoryRepository cashPayHistoryRepository;

    public void pay(CashPayEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getName();

        CashPayHistory cashPayHistory = CashPayHistory.builder()
                .amount(amount)
                .name(name)
                .build();

        cashPayHistoryRepository.save(cashPayHistory);
    }

    public void pay(CardPayEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getCardCompany();

        CardPayHistory cardPayHistory = CardPayHistory.builder()
                .amount(amount)
                .cardCompany(name)
                .build();

        cardPayHistoryRepository.save(cardPayHistory);
    }
}
