package com.javabom.producercomsumer.producercomsumer.pay.domain.event;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public abstract class PaymentEvent {
    private int failCount = 0;

    public abstract Payment toEntity();

    public abstract void consume();

    public void failProcess(Consumer<PaymentEvent> transferEvent) {
        failCount++;
        if (isFail()) {
            log.info("이벤트 처리 실패");
            consume();
            return;
        }
        transferEvent.accept(this);
    }

    private boolean isFail() {
        return failCount > 2;
    }

    protected boolean isSuccess() {
        return !isFail();
    }
}
