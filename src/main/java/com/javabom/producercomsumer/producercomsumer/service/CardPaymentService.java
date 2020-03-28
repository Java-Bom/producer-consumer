package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardPaymentService {

    private final EventBroker<CardPaymentEvent> eventBroker;
    private final AccountRepository accountRepository;

    public void requestPay(final CardPaymentRequestDto cardPaymentRequestDto) {
        eventBroker.offer(new CardPaymentEvent(cardPaymentRequestDto));
    }

    @Transactional
    public void pay(final CardPaymentEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);

        account.cardPay(paymentEvent.getCardPaymentRequestDto());

    }

}
