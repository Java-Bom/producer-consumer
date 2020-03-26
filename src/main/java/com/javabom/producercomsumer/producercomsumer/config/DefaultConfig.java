package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {

    @Bean
    public EventBroker<CardPaymentEvent> chargeEventBroker() {
        return new EventBroker<>();
    }

    @Bean
    public EventBroker<CashPaymentEvent> payEventBroker() {
        return new EventBroker<>();
    }

    @Bean
    public BankConsumer<CardPaymentEvent> chargeEventConsumer(EventBroker<CardPaymentEvent> chargeEventBroker) {
        return new BankConsumer<>(chargeEventBroker);
    }

    @Bean
    public BankConsumer<CashPaymentEvent> payEventConsumer(EventBroker<CashPaymentEvent> payEventBroker) {
        return new BankConsumer<>(payEventBroker);
    }


}
