package com.javabom.producercomsumer.producercomsumer.pay.broker;

import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentEventBrokerTest {

    @DisplayName("지정한 갯수 이상의 이벤트 보관 불가")
    @Test
    void add() {
        //given
        int limit = 3;
        PaymentEventBroker<CashEvent> cashPaymentPaymentEventBroker = new PaymentEventBroker<>(limit);

        //when
        cashPaymentPaymentEventBroker.add(new CashEvent("one", 1, paymentEvent -> {
        }));
        cashPaymentPaymentEventBroker.add(new CashEvent("two", 2, paymentEvent -> {
        }));
        cashPaymentPaymentEventBroker.add(new CashEvent("three", 1, paymentEvent -> {
        }));

        //then
        assertThatThrownBy(() -> cashPaymentPaymentEventBroker.add(new CashEvent("four", 4, paymentEvent -> {
        })))
                .isInstanceOf(IllegalStateException.class);
    }
}