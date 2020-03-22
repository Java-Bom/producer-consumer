package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.domain.Bank;
import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.JavabomEvent;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BankConsumer<T extends JavabomEvent> {

    public BankConsumer(EventBroker<T> eventBroker) {
        new Thread(() -> {
            while (true) {
                try {
                    JavabomEvent javabomEvent = eventBroker.poll();
                    javabomEvent.comma();

                    if (javabomEvent instanceof ChargeEvent) {
                        ChargeRequestDto chargeRequestDto = ((ChargeEvent) javabomEvent).getChargeRequestDto();
                        new Bank().charge().accept(chargeRequestDto);
                    }
                    if (javabomEvent instanceof PayEvent) {
                        PayRequestDto payRequestDto = ((PayEvent) javabomEvent).getPayRequestDto();
                        new Bank().pay().accept(payRequestDto);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
