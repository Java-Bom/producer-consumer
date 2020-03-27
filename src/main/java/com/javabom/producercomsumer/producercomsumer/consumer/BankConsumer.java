package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import com.javabom.producercomsumer.producercomsumer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class BankConsumer<T extends PayEvent, E> {

    public BankConsumer(EventBroker<T> eventBroker, PaymentService<E, T> paymentService) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    PayEvent payEvent = eventBroker.poll();
                    paymentService.pay((T) payEvent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Math.random() + "번 스레드");
        thread.start();

    }
}
