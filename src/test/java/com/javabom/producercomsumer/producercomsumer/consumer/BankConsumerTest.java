package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class BankConsumerTest {

    @DisplayName("BankConsumer는 EventBroker에 Event가 추가되는만큼 소모한다")
    @Test
    void consumer() throws InterruptedException {
        // given
        CountDownLatch countDownLatch = new CountDownLatch(2);
        EventBroker<PayEvent> eventEventBroker = new EventBroker<>();
        BankConsumer<PayEvent> bankConsumer = new BankConsumer<>(eventEventBroker, testConsume(countDownLatch), testThreadExecutor());

        //when
        eventEventBroker.offer(new CardPayEvent(new CardPayRequestDto()));
        eventEventBroker.offer(new CashPayEvent(new CashPayRequestDto("user", "감자", 10000)));

        countDownLatch.await();

        //then
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }

    private ThreadPoolTaskExecutor testThreadExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setThreadGroupName("CARD PAYMENT THREAD");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    private Consumer<PayEvent> testConsume(CountDownLatch countDownLatch) {
        return (testEvent1) -> {
            try {
                Thread.sleep(500);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    // TODO: 실패로직을 Consumer에? 지금은 Service ..
    @DisplayName("consume도중 실패하면 다시 EventBroker에 삽입한다")
    @Test
    void fail() {

    }


}
