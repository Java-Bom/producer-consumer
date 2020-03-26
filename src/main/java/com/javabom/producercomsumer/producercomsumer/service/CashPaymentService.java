package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentService {

    private final EventBroker<CashPaymentEvent> eventBroker;

    public void pay(CashPaymentRequestDto cashPaymentRequestDto) {
        eventBroker.offer(new CashPaymentEvent(cashPaymentRequestDto));
    }
}
