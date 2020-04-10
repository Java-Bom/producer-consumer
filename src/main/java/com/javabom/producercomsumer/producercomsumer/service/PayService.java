package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.CardPay;
import com.javabom.producercomsumer.producercomsumer.domain.CardPayRepository;
import com.javabom.producercomsumer.producercomsumer.domain.CashPay;
import com.javabom.producercomsumer.producercomsumer.domain.CashPayRepository;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PayService {
    private final CardPayRepository cardPayRepository;
    private final CashPayRepository cashPayRepository;

    public void save(CardPayEvent cardPayEvent) {
        CardPay cardPay = CardPay.builder()
                .amount(cardPayEvent.getAmount())
                .cardCompany(cardPayEvent.getCardCompany())
                .build();
        cardPayRepository.save(cardPay);
    }

    public void save(CashPayEvent cashPayEvent) {
        CashPay cashPay = CashPay.builder()
                .amount(cashPayEvent.getAmount())
                .name(cashPayEvent.getName())
                .build();
        cashPayRepository.save(cashPay);
    }
}
