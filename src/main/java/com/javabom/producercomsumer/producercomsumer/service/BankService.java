package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.eventHandler.Broker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/03/21
 */
@Service
@RequiredArgsConstructor
public class BankService {

    private final Broker<CashPayEvent> cashBroker;
    private final Broker<CardPayEvent> cardBroker;

    public void registerCardPayEvent(CardPayRequestDto cardPayRequestDto){
        cardBroker.registerEvent(new CardPayEvent(cardPayRequestDto));
    }

    public void registerCashPayEvent(CashPayRequestDto cashPayRequestDto){
        cashBroker.registerEvent(new CashPayEvent(cashPayRequestDto));
    }
}
