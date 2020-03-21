package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.event.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DafaultConfig {

    @Bean
    public EventBroker<ChargeEvent> chargeEventBroker() {
        return new EventBroker<>();
    }

    @Bean
    public EventBroker<PayEvent> payEventBroker() {
        return new EventBroker<>();
    }

    @Bean
    public BankConsumer<ChargeEvent> chargeEventConsumer(EventBroker<ChargeEvent> chargeEventBroker) throws InterruptedException {
        return new BankConsumer<>(chargeEventBroker);
    }

    @Bean
    public BankConsumer<PayEvent> payEventConsumer(EventBroker<PayEvent> payEventBroker) throws InterruptedException {
        return new BankConsumer<>(payEventBroker);
    }


}
