package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    private final EventBroker<PayEvent> eventBroker;

    public void pay(PayRequestDto payRequestDto) {
        eventBroker.offer(new PayEvent(payRequestDto));

    }
}
