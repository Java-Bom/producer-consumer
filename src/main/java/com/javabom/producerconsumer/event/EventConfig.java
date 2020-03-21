package com.javabom.producerconsumer.event;

import com.javabom.producerconsumer.domain.ChargeFactory;
import com.javabom.producerconsumer.domain.PayFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

@Configuration
public class EventConfig {

    @Bean
    public ConsumerComponent<PayEvent> payConsumer(){
        return new ConsumerComponent<>(payBroker(), PayFactory::pay);
    }

    @Bean
    public ConsumerComponent<ChargeEvent> chargeConsumer(){
        return new ConsumerComponent<>(chargeBroker(), ChargeFactory::charge);
    }

    @Bean
    public RequestBroker<PayEvent> payBroker() {
        return new RequestBroker<>(new LinkedList<>());
    }

    @Bean
    public RequestBroker<ChargeEvent> chargeBroker() {
        return new RequestBroker<>(new LinkedList<>());
    }
}
