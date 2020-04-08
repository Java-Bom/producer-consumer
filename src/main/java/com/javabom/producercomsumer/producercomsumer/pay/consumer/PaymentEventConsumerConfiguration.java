package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import com.javabom.producercomsumer.producercomsumer.pay.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PaymentEventConsumerConfiguration {

    @Bean
    public PaymentEventConsumer<CashPayment> payEventConsumer(PaymentService paymentService) {
        log.info("payEventConsumer bean created.");
        return new PaymentEventConsumer<>(PaymentEventBrokerGroup.PAY.getPaymentEventBroker(), paymentService::saveEvent);
    }

    @Bean
    public PaymentEventConsumer<CardPayment> chargeEventConsumer(PaymentService paymentService) {
        log.info("chargeEventConsumer bean created.");
        return new PaymentEventConsumer<>(PaymentEventBrokerGroup.CHARGE.getPaymentEventBroker(), paymentService::saveEvent);
    }

}
