package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashPaymentService implements PaymentService<CashPaymentRequestDto> {

    private final EventBroker<CashPaymentEvent> eventBroker;
    private final AccountRepository accountRepository;

    public void requestPay(CashPaymentRequestDto cashPaymentRequestDto) {
        eventBroker.offer(new CashPaymentEvent(cashPaymentRequestDto));
    }


    @Override
    @Transactional
    public void pay(CashPaymentRequestDto cashPaymentRequestDto) {
        Account account = accountRepository.findAccountByUserId(cashPaymentRequestDto.getUserId())
                .orElseThrow(IllegalArgumentException::new);

        account.cashPay().accept(cashPaymentRequestDto);
    }
}
