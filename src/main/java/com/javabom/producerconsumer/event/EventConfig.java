package com.javabom.producerconsumer.event;

import com.javabom.producerconsumer.service.CardPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {
    private static final int EVENT_CAPACITY = 100;
    private final CardPayService service;

    @Bean
    public PayConsumer<CardPayEvent> payConsumer() {
        return new PayConsumer<>(new PayRequestBroker<>(EVENT_CAPACITY), service::pay);
    }

}
