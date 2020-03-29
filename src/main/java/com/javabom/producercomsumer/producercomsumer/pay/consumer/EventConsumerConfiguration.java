package com.javabom.producercomsumer.producercomsumer.pay.consumer;

import com.javabom.producercomsumer.producercomsumer.pay.broker.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.PayEvent;
import com.javabom.producercomsumer.producercomsumer.pay.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EventConsumerConfiguration {

    @Bean
    public EventConsumer<PayEvent> payEventConsumer(EventService eventService) {
        log.info("payEventConsumer bean created.");
        return new EventConsumer<>(EventBrokerGroup.PAY.getEventBroker(), eventService::saveEvent);
    }

    @Bean
    public EventConsumer<ChargeEvent> chargeEventConsumer(EventService eventService) {
        log.info("chargeEventConsumer bean created.");
        return new EventConsumer<>(EventBrokerGroup.CHARGE.getEventBroker(), eventService::saveEvent);
    }

}
