package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.event.message.CardPayEvent;
import com.javabom.producerconsumer.event.message.CashPayEvent;
import com.javabom.producerconsumer.event.message.PayEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("소비 실패 케이스")
class PayConsumerFailTest {

    private PayConsumer consumer;

    @AfterEach
    void tearDown() {
        consumer.stop();
        consumer = null;
    }

    @Test
    @DisplayName("카드 이벤트를 3번이상 실패했을때는 재시도하지 않고 실패이력에 남긴다.")
    void consume2() throws InterruptedException {
        //given
        CountDownLatch latch = new CountDownLatch(4);

        CardPayEvent cardPayEvent = CardPayEvent.builder()
                .consumer(failCardConsumer(latch))
                .failConsumer(failConsumer(latch))
                .build();

        PayBrokerGroup payBrokerGroup = new PayBrokerGroup();
        payBrokerGroup.put(CardPayEvent.class, cardPayEvent);

        //when
        //이벤트 수행
        consumer = new PayConsumer(payBrokerGroup);

        latch.await();

        //then
        assertThat(payBrokerGroup.pop(CardPayEvent.class).isPresent()).isFalse();
        assertThat(cardPayEvent.getTryCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("현금 이벤트를 2번이상 실패했을때는 재시도하지 않고 실패이력에 남긴다.")
    void consume() throws InterruptedException {
        //given
        CountDownLatch latch = new CountDownLatch(3);

        CashPayEvent cashPayEvent = CashPayEvent.builder()
                .consumer(failCashConsumer(latch))
                .failConsumer(failConsumer(latch))
                .build();

        PayBrokerGroup payBrokerGroup = new PayBrokerGroup();
        payBrokerGroup.put(CashPayEvent.class, cashPayEvent);

        //when
        //이벤트 수행
        consumer = new PayConsumer(payBrokerGroup);

        latch.await();

        //then
        assertThat(payBrokerGroup.pop(CashPayEvent.class).isPresent()).isFalse();
        assertThat(cashPayEvent.getTryCount()).isEqualTo(2);
    }

    private static Consumer<CardPayEvent> failCardConsumer(CountDownLatch latch) {
        return (cashPayEvent) -> {
            latch.countDown();
            throw new RuntimeException();
        };
    }

    private Consumer<CashPayEvent> failCashConsumer(CountDownLatch latch) {
        return (cashPayEvent) -> {
            latch.countDown();
            throw new RuntimeException();
        };
    }

    private Consumer<PayEvent> failConsumer(CountDownLatch latch) {
        return (payEvent) -> {
            latch.countDown();
        };
    }

}