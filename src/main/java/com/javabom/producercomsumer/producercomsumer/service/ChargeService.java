package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeService {

    private final EventBroker<ChargeEvent> eventBroker;

    public void charge(ChargeRequestDto chargeRequestDto) {
        eventBroker.offer(new ChargeEvent(chargeRequestDto));
    }
}
