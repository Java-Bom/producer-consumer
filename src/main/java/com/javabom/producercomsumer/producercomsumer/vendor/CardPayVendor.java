package com.javabom.producercomsumer.producercomsumer.vendor;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.service.exception.PayFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardPayVendor implements PayVendor<CardPayEvent> {
    @Override
    public void requestPayToVendor(CardPayEvent paymentEvent) {
        log.info("벤더사로 카드 결제요쳥을 보냅니다 ... : {}", paymentEvent.toString());
        paymentEvent.run();
        throw new PayFailException("Exception !!!");
    }
}
