package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;

import java.util.Arrays;

public enum PaymentEventBrokerGroup {
    PAY(new PaymentEventBroker<CashPayment>(), CashPayment.class),
    CHARGE(new PaymentEventBroker<CardPayment>(), CardPayment.class);

    private final PaymentEventBroker<? extends Payment> paymentEventBroker;
    private final Class<? extends Payment> eventClass;

    PaymentEventBrokerGroup(PaymentEventBroker<? extends Payment> paymentEventBroker, Class<? extends Payment> eventClass) {
        this.paymentEventBroker = paymentEventBroker;
        this.eventClass = eventClass;
    }

    @SuppressWarnings("unchecked")
    public static <E extends Payment> PaymentEventBroker<E> findByEvent(E event) {
        return (PaymentEventBroker<E>) Arrays.stream(values())
                .filter(brokerGroup -> brokerGroup.eventClass.equals(event.getClass()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .paymentEventBroker;
    }

    @SuppressWarnings("unchecked")
    public <E extends Payment> PaymentEventBroker<E> getPaymentEventBroker() {
        return (PaymentEventBroker<E>) this.paymentEventBroker;
    }

}
