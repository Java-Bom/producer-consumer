package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.domain.BankEvent;
import com.javabom.producercomsumer.producercomsumer.domain.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.domain.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.BankEventConsumer;
import com.javabom.producercomsumer.producercomsumer.event.Broker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by jyami on 2020/03/21
 */
@Configuration
@Slf4j
public class EventQueueConfiguration {

    @Bean
    Broker<CardPayEvent<?>> brokerCardPayEvent(){
        log.info("-------------------makeBrokerGroup : CardPayEvent-------------------");
        return new Broker<>();
    }

    @Bean
    Broker<CashPayEvent<?>> brokerCardCashPayEvent(){
        log.info("-------------------makeBrokerGroup : CardPayEvent-------------------");
        return new Broker<>();
    }

    @Bean
    BankEventConsumer<CardPayEvent<?>> bankEventConsumerCardPayEvent(){
        log.info("-------------------makeConsumer : CardPayEvent-------------------");
        return new BankEventConsumer<>(brokerCardPayEvent());
    }

    @Bean
    BankEventConsumer<CashPayEvent<?>> bankEventConsumerCashPayEvent(){
        log.info("-------------------makeConsumer : CashPayEvent-------------------");
        return new BankEventConsumer<>(brokerCardCashPayEvent());
    }

}
