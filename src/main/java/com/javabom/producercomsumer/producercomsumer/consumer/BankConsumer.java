package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import com.javabom.producercomsumer.producercomsumer.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class BankConsumer<T extends PayEvent, E> {

    public BankConsumer(EventBroker<T> eventBroker, PaymentService<E> paymentService) {
        new Thread(() -> {
            while (true) {
                try {
                    PayEvent payEvent = eventBroker.poll();
                    payEvent.run();

                    // TODO: 캐스팅 안하겠다고 제네릭 쓰는건데 방법을 모르겠읍니다.
                    if (payEvent instanceof CashPaymentEvent) {
                        paymentService.pay((E) ((CashPaymentEvent) payEvent).getCashPaymentRequestDto());
                    }
                    if (payEvent instanceof CardPaymentEvent) {
                        paymentService.pay((E) ((CardPaymentEvent) payEvent).getCardPaymentRequestDto());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
