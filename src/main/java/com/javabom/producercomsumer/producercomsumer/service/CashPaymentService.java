package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.support.PayVendor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashPaymentService {

    private final EventBroker<CashPaymentEvent> eventBroker;
    private final AccountRepository accountRepository;
    private final PayVendor cashPayVendor;

    public void requestPay(final CashPaymentRequestDto cashPaymentRequestDto) {
        eventBroker.offer(new CashPaymentEvent(cashPaymentRequestDto));
    }

    @Transactional
    public void pay(final CashPaymentEvent paymentEvent) {
        if (!successRequestPayToVendor(paymentEvent)) {
            recordFailToCashPayment(paymentEvent);
            return;
        }
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), true);
    }

    private boolean successRequestPayToVendor(CashPaymentEvent paymentEvent) {
        return cashPayVendor.requestPayToVendor(paymentEvent);
    }


    private void recordFailToCashPayment(CashPaymentEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCashPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cashPay(paymentEvent.getCashPaymentRequestDto(), false);
    }

}
