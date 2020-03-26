package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.service.CardPaymentService;
import com.javabom.producercomsumer.producercomsumer.service.CashPaymentService;
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
    public BankConsumer<CardPaymentEvent, CardPaymentRequestDto> chargeEventConsumer(EventBroker<CardPaymentEvent> chargeEventBroker, CardPaymentService cardPaymentService) {
        return new BankConsumer<>(chargeEventBroker, cardPaymentService);
    }

    @Bean
    public BankConsumer<CashPaymentEvent, CashPaymentRequestDto> payEventConsumer(EventBroker<CashPaymentEvent> payEventBroker, CashPaymentService cashPaymentService) {
        return new BankConsumer<>(payEventBroker, cashPaymentService);
    }


}
