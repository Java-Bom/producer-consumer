package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendorGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashPayService {

    private final ApplicationEventPublisher publisherForRollback;
    private final AccountRepository accountRepository;

    @Transactional
    public void pay(final CashPayEvent payEvent) {
        publisherForRollback.publishEvent(payEvent);

        PayVendorGroup.findByPayEvent(CashPayEvent.class).requestPayToVendor(payEvent);

        Account account = accountRepository.findAccountByUserId(payEvent.getCashPayRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(payEvent.getCashPayRequestDto(), true);
    }

    @Transactional
    public void recordOfFailure(final CashPayEvent cashPayEvent) {
        Account account = accountRepository.findAccountByUserId(cashPayEvent.getCashPayRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(cashPayEvent.getCashPayRequestDto(), false);
    }
}
