package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.service.CardPaymentService;
import com.javabom.producercomsumer.producercomsumer.service.CashPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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
    public BankConsumer<CardPaymentEvent> chargeEventConsumer(EventBroker<CardPaymentEvent> chargeEventBroker, CardPaymentService cardPaymentService, ThreadPoolTaskExecutor cardPayThreadPool) {
        return new BankConsumer<>(chargeEventBroker, cardPaymentService::pay, cardPayThreadPool);
    }

    @Bean
    public BankConsumer<CashPaymentEvent> payEventConsumer(EventBroker<CashPaymentEvent> payEventBroker, CashPaymentService cashPaymentService, ThreadPoolTaskExecutor cashPayThreadPool) {
        return new BankConsumer<>(payEventBroker, cashPaymentService::pay, cashPayThreadPool);
    }


    @Bean
    public ThreadPoolTaskExecutor cardPayThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setThreadGroupName("CARD PAYMENT THREAD");
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor cashPayThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setThreadGroupName("CASH PAYMENT THREAD");
        return threadPoolTaskExecutor;
    }

}
