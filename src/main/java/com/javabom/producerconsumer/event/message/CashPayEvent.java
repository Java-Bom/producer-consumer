package com.javabom.producerconsumer.event.message;

import com.javabom.producerconsumer.domain.FailRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class CashPayEvent extends PayEvent {
    private static final int MAX_TRY_COUNT = 2;
    private Long amount;
    private String name;
    private Consumer<CashPayEvent> consumer;

    @Builder
    private CashPayEvent(Long amount, String name, Consumer<CashPayEvent> consumer, Consumer<PayEvent> failConsumer) {
        super(failConsumer);
        this.amount = amount;
        this.name = name;
        this.consumer = consumer;
    }

    @Override
    public String comma() {
        return String.format("%d,%s", amount, name);
    }

    @Override
    public FailRequest toFail() {
        return new FailRequest(PayType.CASH);
    }

    @Override
    protected void pay() {
        consumer.accept(this);
    }

    @Override
    public boolean isEnableRetry() {
        return super.tryCount != MAX_TRY_COUNT;
    }
}
