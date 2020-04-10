package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.event.PayEventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEventConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    @Bean
    public PayEventConsumer payEventConsumer() {
        return new PayEventConsumer(payEventBroker());
    }

    @Bean
    public PayEventBroker payEventBroker() {
        return new PayEventBroker(100);
    }

}
