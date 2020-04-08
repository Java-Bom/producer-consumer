package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CardEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;

import java.util.Arrays;

public enum PaymentEventBrokerGroup {
    CARD(new PaymentEventBroker<CardEvent>(100), CardEvent.class),
    CASH(new PaymentEventBroker<CashEvent>(100), CashEvent.class);

    private final PaymentEventBroker<? extends PaymentEvent> paymentEventBroker;
    private final Class<? extends PaymentEvent> eventClass;

    PaymentEventBrokerGroup(PaymentEventBroker<? extends PaymentEvent> paymentEventBroker, Class<? extends PaymentEvent> eventClass) {
        this.paymentEventBroker = paymentEventBroker;
        this.eventClass = eventClass;
    }

    @SuppressWarnings("unchecked")
    public static <E extends PaymentEvent> PaymentEventBroker<E> findByEvent(E event) {
        return (PaymentEventBroker<E>) Arrays.stream(values())
                .filter(brokerGroup -> brokerGroup.eventClass.equals(event.getClass()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .paymentEventBroker;
    }

    @SuppressWarnings("unchecked")
    public <E extends PaymentEvent> PaymentEventBroker<E> getPaymentEventBroker() {
        return (PaymentEventBroker<E>) this.paymentEventBroker;
    }

}
