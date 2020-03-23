package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.domain.CardPayHistory;
import com.javabom.producerconsumer.domain.CardPayHistoryRepository;
import com.javabom.producerconsumer.event.CardPayEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardPayService {

    private final CardPayHistoryRepository repository;

    public void pay(CardPayEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getCardCompany();

        CardPayHistory cardPayHistory = CardPayHistory.builder()
                .amount(amount)
                .cardCompany(name)
                .build();

        repository.save(cardPayHistory);
    }
}
