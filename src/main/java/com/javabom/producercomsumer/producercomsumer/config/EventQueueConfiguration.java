package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.eventHandler.BankEventConsumer;
import com.javabom.producercomsumer.producercomsumer.eventHandler.Broker;
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
    Broker<CardPayEvent> brokerCardPayEvent(){
        log.info("-------------------makeBrokerGroup : CardPayEvent-------------------");
        return new Broker<>();
    }

    @Bean
    Broker<CashPayEvent> brokerCashPayEvent(){
        log.info("-------------------makeBrokerGroup : CashPayEvent-------------------");
        return new Broker<>();

    }

    @Bean
    BankEventConsumer<CardPayEvent> bankEventConsumerCardPayEvent(){
        log.info("-------------------makeConsumer : CardPayEvent-------------------");
        BankEventConsumer<CardPayEvent> cardConsumer = new BankEventConsumer<>(brokerCardPayEvent());
        new Thread(cardConsumer, "card-thread").start();
        return cardConsumer;

    }

    @Bean
    BankEventConsumer<CashPayEvent> bankEventConsumerCashPayEvent(){
        log.info("-------------------makeConsumer : CashPayEvent-------------------");
        BankEventConsumer<CashPayEvent> cashConsumer = new BankEventConsumer<>(brokerCashPayEvent());
        new Thread(cashConsumer, "cash-thread").start();
        return cashConsumer;
    }

}
