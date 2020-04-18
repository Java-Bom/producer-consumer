package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentEventTest {

    @DisplayName("실패 과정을 거치지 않고 저장하면 success 상태로 저장한다")
    @Test
    void name() {
        //given
        PaymentEvent paymentEvent = CashEvent.builder()
                .money(1000)
                .name("박찬인")
                .consumer(cashEvent -> {
                })
                .build();

        //when
        Payment payment = paymentEvent.toEntity();

        //then
        assertThat(paymentEvent.isSuccess()).isTrue();
        assertThat(payment.isSuccess()).isTrue();
    }

    @DisplayName("2번 이상 실패하면 fail 상태로 저장한다.")
    @Test
    void failProcess() {
        //given
        PaymentEvent paymentEvent = CashEvent.builder()
                .money(1000)
                .name("박찬인")
                .consumer(cashEvent -> {
                })
                .build();

        //when
        cashConsumer(paymentEvent);
        Payment payment = paymentEvent.toEntity();

        //then
        assertThat(paymentEvent.isSuccess()).isFalse();
        assertThat(payment.isSuccess()).isFalse();
    }

    private void cashConsumer(PaymentEvent paymentEvent) {
        paymentEvent.failProcess(this::cashConsumer);
    }
}