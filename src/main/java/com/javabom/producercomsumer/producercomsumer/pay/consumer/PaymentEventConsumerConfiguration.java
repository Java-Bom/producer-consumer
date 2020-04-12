package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CardEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PaymentEventConsumerConfiguration {

    @Bean
    public PaymentEventConsumer<CardEvent> cardEventConsumer() {
        log.info("cardEventConsumer bean created.");
        return new PaymentEventConsumer<>(PaymentEventBrokerGroup.CARD.getPaymentEventBroker(), "CARD");
    }

    @Bean
    public PaymentEventConsumer<CashEvent> cashEventConsumer() {
        log.info("cashEventConsumer bean created.");
        return new PaymentEventConsumer<>(PaymentEventBrokerGroup.CASH.getPaymentEventBroker(), "CASH");
    }

}
