package com.javabom.producercomsumer.producercomsumer.event.handler;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayEventRequestHandler {

    @EventListener
    public void requestPay(CardPayRequestDto payRequestDto) {
        log.info("요청이 왔습니다.");
        EventBrokerGroup.findPayEventBroker(CardPayEvent.class).offer(CardPayEvent.of(payRequestDto));
    }

    @EventListener
    public void requestPay(CashPayRequestDto payEvent) {
        EventBrokerGroup.findPayEventBroker(CashPayEvent.class).offer(CashPayEvent.of(payEvent));
    }

}
