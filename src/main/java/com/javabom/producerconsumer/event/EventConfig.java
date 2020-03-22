package com.javabom.producerconsumer.event;

import com.javabom.producerconsumer.service.CardPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

@Configuration
@RequiredArgsConstructor
public class EventConfig {
    private final CardPayService service;

    @Bean
    public PayConsumer<CardPayEvent> payConsumer() {
        return new PayConsumer<>(new PayRequestBroker<>(new LinkedList<>()), service::pay);
    }

}
