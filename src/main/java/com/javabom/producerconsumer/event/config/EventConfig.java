package com.javabom.producerconsumer.event.config;

import com.javabom.producerconsumer.domain.FailRequestRepository;
import com.javabom.producerconsumer.event.process.PayBrokerGroup;
import com.javabom.producerconsumer.event.process.PayConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

    private final FailRequestRepository requestRepository;

    @Bean
    public PayConsumer cardPayConsumer() {
        return new PayConsumer(payBrokerGroupMap(), requestRepository);
    }

    @Bean
    public PayBrokerGroup payBrokerGroupMap() {
        return new PayBrokerGroup();
    }
}
