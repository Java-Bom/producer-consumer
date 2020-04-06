package com.javabom.producercomsumer.producercomsumer.support;

import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;
import com.javabom.producercomsumer.producercomsumer.service.exception.PayFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CardPayVendor implements PayVendor {
    @Retryable(value = PayFailException.class, backoff = @Backoff(delay = 1000))
    public boolean requestPayToVendor(PaymentEvent paymentEvent) {
        log.info("벤더사로 카드 결제요쳥을 보냅니다 ... : {}", paymentEvent.toString());
        throw new PayFailException("카드결제 중 익셉션 발생");
//        return true;
    }

    @Recover
    public boolean recoverPayEventFailure(PayFailException e, PaymentEvent paymentEvent) {
        log.info("카드결제실패 후 실패응답을 반환합니다 .. ", paymentEvent.toString());
        return false;
    }
}
