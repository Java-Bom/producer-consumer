package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.domain.CashPayHistory;
import com.javabom.producerconsumer.domain.CashPayHistoryRepository;
import com.javabom.producerconsumer.event.CashPayEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashPayService {

    private final CashPayHistoryRepository repository;

    public void pay(CashPayEvent event) {
        final Long amount = event.getAmount();
        final String name = event.getName();

        CashPayHistory cashPayHistory = CashPayHistory.builder()
                .amount(amount)
                .name(name)
                .build();
        repository.save(cashPayHistory);
    }

}
