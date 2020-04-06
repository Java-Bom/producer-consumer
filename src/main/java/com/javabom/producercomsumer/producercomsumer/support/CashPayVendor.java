package com.javabom.producercomsumer.producercomsumer.support;

import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CashPayVendor implements PayVendor<CashPaymentEvent> {
    @Override
    public void requestPayToVendor(CashPaymentEvent paymentEvent) {
        log.info("벤더사로 카드 결제요쳥을 보냅니다 ... : {}", paymentEvent.toString());
        paymentEvent.run();
    }
}
