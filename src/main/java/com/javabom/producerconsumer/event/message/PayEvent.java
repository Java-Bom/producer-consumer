package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@NoArgsConstructor
public abstract class PayEvent {
    private static final int DEFAULT_MAX_TRY_COUNT = 3;

    protected int tryCount;
    protected Consumer<PayEvent> failConsumer;

    public PayEvent(Consumer<PayEvent> failConsumer) {
        this.failConsumer = failConsumer;
    }


    public void consume() {
        tryCount++;
        pay();
    }

    public void consumeFail() {
        failConsumer.accept(this);
    }

    public boolean isEnableRetry() {
        return tryCount != DEFAULT_MAX_TRY_COUNT;
    }

    public abstract String comma();

    public abstract FailRequest toFail();

    protected abstract void pay();
}
