package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import com.javabom.producerconsumer.exception.FailRequestException;
import lombok.Getter;

public abstract class PayEvent {
    private static final int MAX_TRY_COUNT = 3;

    @Getter
    private int tryCount;

    public abstract String comma();

    public abstract void consume();

    public abstract FailRequest toFail();

    public void tryPay() {
        if (tryCount == MAX_TRY_COUNT) {
            throw new FailRequestException();
        }
        tryCount++;
    }
}
