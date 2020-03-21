package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.event.ChargeEvent;
import com.javabom.producerconsumer.event.RequestBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeService {
    private final RequestBroker<ChargeEvent> chargeBroker;


    public void register(ChargeEvent event){
        chargeBroker.push(event);
    }
}
