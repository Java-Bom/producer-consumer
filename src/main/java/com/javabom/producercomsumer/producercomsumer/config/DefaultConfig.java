package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.consumer.PayFailureConsumer;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.service.CardPayService;
import com.javabom.producercomsumer.producercomsumer.service.CashPayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class DefaultConfig {

    @Bean
    public BankConsumer<CardPayEvent> chargeEventConsumer(CardPayService cardPayService, ThreadPoolTaskExecutor cardPayThreadPool) {
        return new BankConsumer<>(EventBrokerGroup.findPayEventBroker(CardPayEvent.class), cardPayService::pay, cardPayThreadPool);
    }

    @Bean
    public BankConsumer<CashPayEvent> payEventConsumer(CashPayService cashPayService, ThreadPoolTaskExecutor cashPayThreadPool) {
        return new BankConsumer<>(EventBrokerGroup.findPayEventBroker(CashPayEvent.class), cashPayService::pay, cashPayThreadPool);
    }

    @Bean
    public PayFailureConsumer<CardPayEvent> cardPayEventPayFailureConsumer(CardPayService cardPayService, ThreadPoolTaskExecutor cardPayThreadPool) {
        return new PayFailureConsumer<>(EventBrokerGroup.findFailureEventBroker(CardPayEvent.class), cardPayService::recordOfFailure, cardPayThreadPool);
    }

    @Bean
    public PayFailureConsumer<CashPayEvent> cashPayEventPayFailureConsumer(CashPayService cashPayService, ThreadPoolTaskExecutor cashPayThreadPool) {
        return new PayFailureConsumer<>(EventBrokerGroup.findFailureEventBroker(CashPayEvent.class), cashPayService::recordOfFailure, cashPayThreadPool);
    }

    @Bean
    public ThreadPoolTaskExecutor cardPayThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(15); // 이만큼 늘어났을 때 스레드풀이 늘어난다.
        threadPoolTaskExecutor.setThreadGroupName("CARD PAYMENT THREAD");
        return threadPoolTaskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor cashPayThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(15); // 이만큼 늘어났을 때 스레드풀이 늘어난다.
        threadPoolTaskExecutor.setThreadGroupName("CASH PAYMENT THREAD");
        return threadPoolTaskExecutor;
    }

}
