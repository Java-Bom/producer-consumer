package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendorGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardPayService {

    private final ApplicationEventPublisher publisherForRollback;
    private final AccountRepository accountRepository;

    @Transactional
    public void pay(final CardPayEvent payEvent) {
        publisherForRollback.publishEvent(payEvent);

        PayVendorGroup.findByPayEvent(CardPayEvent.class).requestPayToVendor(payEvent);
        Account account = accountRepository.findAccountByUserId(payEvent.getCardPayRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cardPay(payEvent.getCardPayRequestDto(), true);

        throw new RuntimeException("Exception from " + Thread.currentThread().getName());
    }

    @Transactional
    public void recordOfFailure(final CardPayEvent cardPayEvent) {
        Account account = accountRepository.findAccountByUserId(cardPayEvent.getCardPayRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cardPay(cardPayEvent.getCardPayRequestDto(), false);
    }

}
