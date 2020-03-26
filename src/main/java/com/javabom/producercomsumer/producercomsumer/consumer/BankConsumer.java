package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.JavabomEvent;
import com.javabom.producercomsumer.producercomsumer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class BankConsumer<T extends JavabomEvent, E> {

    public BankConsumer(EventBroker<T> eventBroker, PaymentService<E> paymentService) {
        new Thread(() -> {
            while (true) {
                try {
                    JavabomEvent javabomEvent = eventBroker.poll();
                    javabomEvent.comma();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
