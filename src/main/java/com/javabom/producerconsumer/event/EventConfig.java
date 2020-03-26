package com.javabom.producerconsumer.event;

import com.javabom.producerconsumer.service.CardPayService;
import com.javabom.producerconsumer.service.CashPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {
    public static final int EVENT_CAPACITY = 100;

    private final CardPayService cardPayService;
    private final CashPayService cashPayService;

    @Bean
    public PayConsumer<CardPayEvent> cardPayConsumer() {
        return new PayConsumer<>(CardPayEvent.class, cardPayService::pay);
    }

    @Bean
    public PayConsumer<CashPayEvent> cashPayConsumer() {
        return new PayConsumer<>(CashPayEvent.class, cashPayService::pay);
    }
}
