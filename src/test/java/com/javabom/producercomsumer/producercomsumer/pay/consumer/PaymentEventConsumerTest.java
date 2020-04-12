package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CardEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentEventConsumerTest {

    @DisplayName("생산자 소비자 테스트")
    @Test
    void run() throws InterruptedException {
        //given
        Map<String, Integer> hitTable = new HashMap<>();
        hitTable.put("CASH", 0);
        hitTable.put("CARD", 0);

        PaymentEventConsumerConfiguration paymentEventConsumerConfiguration = new PaymentEventConsumerConfiguration();
        PaymentEventConsumer<CardEvent> cardEventPaymentEventConsumer = paymentEventConsumerConfiguration.cardEventConsumer();
        PaymentEventConsumer<CashEvent> cashEventPaymentEventConsumer = paymentEventConsumerConfiguration.cashEventConsumer();

        CountDownLatch countDownLatch = new CountDownLatch(4);

        PaymentEvent event1 = CardEvent.builder()
                .cardName("카카오뱅크")
                .money(10)
                .consumer(event -> hit(hitTable, countDownLatch)).build();
        PaymentEvent event2 = CardEvent.builder()
                .cardName("카카오뱅크")
                .money(10)
                .consumer(event -> hit(hitTable, countDownLatch)).build();
        PaymentEvent event3 = CashEvent.builder()
                .name("박찬인")
                .money(10)
                .consumer(event -> hit(hitTable, countDownLatch)).build();
        PaymentEvent event4 = CashEvent.builder()
                .name("박찬인")
                .money(10)
                .consumer(event -> hit(hitTable, countDownLatch)).build();

        //when
        PaymentEventBroker<PaymentEvent> eventBroker1 = PaymentEventBrokerGroup.findByEvent(event1);
        PaymentEventBroker<PaymentEvent> eventBroker2 = PaymentEventBrokerGroup.findByEvent(event2);
        PaymentEventBroker<PaymentEvent> eventBroker3 = PaymentEventBrokerGroup.findByEvent(event3);
        PaymentEventBroker<PaymentEvent> eventBroker4 = PaymentEventBrokerGroup.findByEvent(event4);

        eventBroker1.add(event1);
        eventBroker2.add(event2);
        eventBroker3.add(event3);
        eventBroker4.add(event4);

        countDownLatch.await();
        cardEventPaymentEventConsumer.stop();
        cashEventPaymentEventConsumer.stop();

        //then
        assertThat(hitTable.isEmpty()).isFalse();
        assertThat(hitTable.get("CARD")).isEqualTo(2);
        assertThat(hitTable.get("CASH")).isEqualTo(2);
    }

    private void hit(Map<String, Integer> hitMap, CountDownLatch countDownLatch) {
        String name = Thread.currentThread().getName();
        int nowCount = hitMap.get(name);

        hitMap.put(name, nowCount + 1);

        countDownLatch.countDown();
    }
}