package com.javabom.producercomsumer.producercomsumer.vendor;

import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CashPayVendor implements PayVendor<CashPayEvent> {
    @Override
    public void requestPayToVendor(CashPayEvent paymentEvent) {
        log.info("벤더사로 카드 결제요쳥을 보냅니다 ... : {}", paymentEvent.toString());
        paymentEvent.run();
    }
}
