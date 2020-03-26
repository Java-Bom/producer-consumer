package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.domain.Bank;
import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.JavabomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class BankConsumer<T extends JavabomEvent> {

    public BankConsumer(EventBroker<T> eventBroker) {
        new Thread(() -> {
            while (true) {
                try {
                    JavabomEvent javabomEvent = eventBroker.poll();
                    javabomEvent.comma();
                    if (javabomEvent instanceof CardPaymentEvent) {
                        CardPaymentRequestDto cardPaymentRequestDto = ((CardPaymentEvent) javabomEvent).getCardPaymentRequestDto();
                        Bank.charge().accept(cardPaymentRequestDto);
                    }
                    if (javabomEvent instanceof CashPaymentEvent) {
                        CashPaymentRequestDto cashPaymentRequestDto = ((CashPaymentEvent) javabomEvent).getCashPaymentRequestDto();
                        Bank.pay().accept(cashPaymentRequestDto);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
