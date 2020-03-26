package com.javabom.producerconsumer.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("이벤트 소비자 테스트")
class PayConsumerTest {

    @Test
    @DisplayName("이벤트 소비자는 한가지 타입의 이벤트만 소비가능하다.")
    void consume() throws InterruptedException {
        //given
        CardPayEvent cardPayEvent = CardPayEvent.testBuilder()
                .cardCompany("신한카드")
                .amount(2000L)
                .build();

        CardPayEvent cardPayEvent2 = CardPayEvent.testBuilder()
                .cardCompany("현대카드")
                .amount(2000L)
                .build();

        CashPayEvent cashPayEvent = CashPayEvent.testBuilder()
                .amount(3000L)
                .name("최유성")
                .build();

        //when
        PayBrokerGroup.put(CardPayEvent.class, cardPayEvent);
        PayBrokerGroup.put(CashPayEvent.class, cashPayEvent);
        PayBrokerGroup.put(CardPayEvent.class, cardPayEvent2);

        List<String> actualList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);

        //이벤트 수행
        PayConsumer<CardPayEvent> consumer = new PayConsumer<>(CardPayEvent.class, consumer(actualList, latch));
        latch.await();

        //then
        assertThat(actualList.size()).isEqualTo(2);
        assertThat(actualList).contains(cardPayEvent.comma());
        assertThat(actualList).contains(cardPayEvent2.comma());

        consumer.stop();
    }


    //// TODO: 2020. 3. 26.  실패할 수도 있을거같다.
    @Test
    @DisplayName("이벤트 소비는 한개의 스레드가 한다.")
    void consume2() throws InterruptedException {
        //given
        CardPayEvent cardPayEvent = CardPayEvent.testBuilder()
                .cardCompany("신한카드")
                .amount(2000L)
                .build();

        CardPayEvent cardPayEvent2 = CardPayEvent.testBuilder()
                .cardCompany("삼성카드")
                .amount(2000L)
                .build();

        //when
        PayBrokerGroup.put(CardPayEvent.class, cardPayEvent);
        PayBrokerGroup.put(CardPayEvent.class, cardPayEvent2);

        List<String> actualList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        //이벤트 수행
        PayConsumer<CardPayEvent> consumer = new PayConsumer<>(CardPayEvent.class, consumer(actualList, latch));
        latch.await();

        //then
        assertThat(actualList.size()).isEqualTo(1);
        assertThat(actualList).contains(cardPayEvent.comma());
        consumer.stop();
    }


    private Consumer<CardPayEvent> consumer(List<String> commaStrings, CountDownLatch latch) {
        return (cardPayEvent) -> {
            commaStrings.add(cardPayEvent.comma());
            latch.countDown();
        };
    }

}