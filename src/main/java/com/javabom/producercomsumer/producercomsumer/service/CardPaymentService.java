package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardPaymentService {

    private final EventBroker<CardPaymentEvent> eventBroker;

    public void charge(CardPaymentRequestDto cardPaymentRequestDto) {
        eventBroker.offer(new CardPaymentEvent(cardPaymentRequestDto));
    }
}
