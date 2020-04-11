package com.javabom.producerconsumer.event.config;

import com.javabom.producerconsumer.event.process.PayBrokerGroup;
import com.javabom.producerconsumer.event.process.PayConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {
    @Bean
    public PayConsumer cardPayConsumer() {
        return new PayConsumer(payBrokerGroupMap());
    }

    @Bean
    public PayBrokerGroup payBrokerGroupMap() {
        return new PayBrokerGroup();
    }
}
