package com.javabom.producerconsumer.service;

import com.javabom.producerconsumer.event.PayEvent;
import com.javabom.producerconsumer.event.RequestBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    private final RequestBroker<PayEvent> payBroker;


    public void register(PayEvent payEvent){
        payBroker.push(payEvent);
    }
}
