package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class PaymentEventConsumer<E extends Payment> implements Runnable {

    private final PaymentEventBroker<E> paymentEventBroker;
    private final Consumer<E> eventConsumer;

    public PaymentEventConsumer(PaymentEventBroker<E> paymentEventBroker, Consumer<E> eventConsumer) {
        this.paymentEventBroker = paymentEventBroker;
        this.eventConsumer = eventConsumer;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (paymentEventBroker.isEmpty()) {
                continue;
            }
            eventConsumer.accept(paymentEventBroker.poll());
        }
    }
}
