package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendor;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendorGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashPayService {

    private final AccountRepository accountRepository;
    private final PayVendor<CashPayEvent> cashPayVendor = PayVendorGroup.findByPayEvent(CashPayEvent.class);
    private final EventBroker<CashPayEvent> cashPayEventBroker = EventBrokerGroup.findPayEventBroker(CashPayEvent.class);

    public void requestPay(final CashPaymentRequestDto cashPaymentRequestDto) {
        cashPayEventBroker.offer(new CashPayEvent(cashPaymentRequestDto));
    }

    @Transactional
    public void pay(final CashPayEvent paymentEvent) {
        cashPayVendor.requestPayToVendor(paymentEvent);
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), true);
    }

    @Transactional
    public void recordOfFailure(CashPayEvent cashPayEvent) {
        Account account = accountRepository.findAccountByUserId(cashPayEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(cashPayEvent.getCashPaymentRequestDto(), false);
    }

    private void requestPay(CashPayEvent paymentEvent) {
        if (paymentEvent.isMaximumTry()) {
            recordFailToCashPayment(paymentEvent);
            return;
        }
        EventBrokerGroup.findPayEventBroker(CashPayEvent.class).offer(paymentEvent);
    }

    private void recordFailToCashPayment(CashPayEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), false);
    }
}
