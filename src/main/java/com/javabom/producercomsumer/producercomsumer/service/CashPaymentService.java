package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.service.exception.PayFailException;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashPaymentService {

    private final AccountRepository accountRepository;
    private final PayVendor cashPayVendor;

    public void requestPay(final CashPaymentRequestDto cashPaymentRequestDto) {
        EventBrokerGroup.findByPayEvent(CashPayEvent.class).offer(new CashPayEvent(cashPaymentRequestDto));
    }

    @Transactional
    public void pay(final CashPayEvent paymentEvent) {
        try {
            cashPayVendor.requestPayToVendor(paymentEvent);
        } catch (PayFailException payFailException) {
            requestPay(paymentEvent);
            return;
        }

        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), true);
    }

    private void requestPay(CashPayEvent paymentEvent) {
        if (paymentEvent.isMaximumTry()) {
            recordFailToCashPayment(paymentEvent);
            return;
        }
        EventBrokerGroup.findByPayEvent(CashPayEvent.class).offer(paymentEvent);
    }

    private void recordFailToCashPayment(CashPayEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), false);
    }

}
