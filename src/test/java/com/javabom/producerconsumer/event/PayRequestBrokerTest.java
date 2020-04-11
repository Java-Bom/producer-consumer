package com.javabom.producerconsumer.event;

import com.javabom.producerconsumer.domain.FailRequest;
import com.javabom.producerconsumer.event.message.PayEvent;
import com.javabom.producerconsumer.event.process.PayRequestBroker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("결제 이벤트큐 테스트")
class PayRequestBrokerTest {


    @Test
    @DisplayName("결제 이벤트를 10개 이상 담을 수 없다.")
    void name() {
        //given
        final int capacity = 10;

        PayRequestBroker<TestEvent> broker = new PayRequestBroker<>(capacity);
        //when

        for (int i = 1; i <= capacity; i++) {
            TestEvent testEvent = new TestEvent(i);
            broker.push(testEvent);
        }

        //then
        assertThatThrownBy(() -> broker.push(new TestEvent(capacity + 1)))
                .isInstanceOf(IllegalStateException.class);
    }


    static class TestEvent extends PayEvent {
        private Integer id;

        public TestEvent(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public String comma() {
            return id.toString();
        }

        @Override
        public FailRequest toFail() {
            return null;
        }

        @Override
        protected void pay() {

        }
    }
}