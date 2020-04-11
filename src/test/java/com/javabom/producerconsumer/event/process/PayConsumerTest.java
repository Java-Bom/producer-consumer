package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.CardPayEvent;
import com.javabom.producerconsumer.event.message.CashPayEvent;
import com.javabom.producerconsumer.event.message.PayType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static com.javabom.producerconsumer.event.message.PayType.CARD;
import static com.javabom.producerconsumer.event.message.PayType.CASH;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("이벤트 소비자 테스트")
class PayConsumerTest {

    private PayConsumer consumer;

    @AfterEach
    void tearDown() {
        consumer.stop();
        consumer = null;
    }

    @Test
    @DisplayName("이벤트 소비자는 그룹에 들어있는 이벤트 큐만큼 스레드를 생성해 이벤트를 소비한다")
    void consume() throws InterruptedException {
        //given
        CountDownLatch latch = new CountDownLatch(4);
        Map<String, Integer> hitMap = hitMap();

        CardPayEvent cardPayEvent = CardPayEvent.builder()
                .consumer(cardConsumer(hitMap, latch))
                .build();

        CardPayEvent cardPayEvent2 = CardPayEvent.builder()
                .consumer(cardConsumer(hitMap, latch))
                .build();

        CashPayEvent cashPayEvent = CashPayEvent.builder()
                .consumer(cashConsumer(hitMap, latch))
                .build();

        CashPayEvent cashPayEvent2 = CashPayEvent.builder()
                .consumer(cashConsumer(hitMap, latch))
                .build();


        PayBrokerGroup payBrokerGroup = new PayBrokerGroup();

        payBrokerGroup.put(CardPayEvent.class, cardPayEvent);
        payBrokerGroup.put(CardPayEvent.class, cardPayEvent2);
        payBrokerGroup.put(CashPayEvent.class, cashPayEvent);
        payBrokerGroup.put(CashPayEvent.class, cashPayEvent2);
        //when
        //이벤트 수행
        consumer = new PayConsumer(payBrokerGroup);
        latch.await();

        //then
        assertThat(hitMap.get("thread_" + CARD.getEventClass().getSimpleName())).isEqualTo(2);
        assertThat(hitMap.get("thread_" + CASH.getEventClass().getSimpleName())).isEqualTo(2);
    }


    private Consumer<CardPayEvent> cardConsumer(Map<String, Integer> hitMap, CountDownLatch latch) {
        return (cardPayEvent) -> {
            Integer count = hitMap.get(Thread.currentThread().getName());
            hitMap.put(Thread.currentThread().getName(), count + 1);
            latch.countDown();
        };
    }

    private Consumer<CashPayEvent> cashConsumer(Map<String, Integer> hitMap, CountDownLatch latch) {
        return (cashPayEvent) -> {
            Integer count = hitMap.get(Thread.currentThread().getName());
            hitMap.put(Thread.currentThread().getName(), count + 1);
            latch.countDown();
        };
    }


    private Map<String, Integer> hitMap() {
        Map<String, Integer> hitMap = new HashMap<>();
        for (PayType type : PayType.values()) {
            hitMap.put("thread_" + type.getEventClass().getSimpleName(), 0);
        }
        return hitMap;
    }

}