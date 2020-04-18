package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentEventConsumer<E extends PaymentEvent> implements Runnable {

    private final PaymentEventBroker<E> paymentEventBroker;
    private boolean runnable = true;

    public PaymentEventConsumer(PaymentEventBroker<E> paymentEventBroker, String name) {
        this.paymentEventBroker = paymentEventBroker;
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        while (runnable) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (paymentEventBroker.isEmpty()) {
                continue;
            }
            paymentEventBroker.poll()
                    .consume();
        }
    }

    public void stop() {
        this.runnable = false;
    }

}
