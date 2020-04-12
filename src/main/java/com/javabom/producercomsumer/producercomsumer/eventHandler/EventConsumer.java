package com.javabom.producercomsumer.producercomsumer.eventHandler;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/03/27
 */
@RequiredArgsConstructor
@Component
public class EventConsumer {

    private final BankEventConsumer<CardPayEvent> cardPayEventBankEventConsumer;
    private final BankEventConsumer<CashPayEvent> cashPayEventBankEventConsumer;

    public void run(){
        new Thread(cardPayEventBankEventConsumer).start();
        new Thread(cashPayEventBankEventConsumer).start();
    }

    public void shutDownRun(){
        cardPayEventBankEventConsumer.shutDownRun();
        cashPayEventBankEventConsumer.shutDownRun();
    }
}
