package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.consumer.BankConsumer;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.service.CardPaymentService;
import com.javabom.producercomsumer.producercomsumer.service.CashPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class DefaultConfig {

    @Bean
    public BankConsumer<CardPayEvent> chargeEventConsumer(CardPaymentService cardPaymentService, ThreadPoolTaskExecutor cardPayThreadPool) {
        return new BankConsumer<>(EventBrokerGroup.findByPayEvent(CardPayEvent.class), cardPaymentService::pay, cardPayThreadPool);
    }

    @Bean
    public BankConsumer<CashPayEvent> payEventConsumer(CashPaymentService cashPaymentService, ThreadPoolTaskExecutor cashPayThreadPool) {
        return new BankConsumer<>(EventBrokerGroup.findByPayEvent(CashPayEvent.class), cashPaymentService::pay, cashPayThreadPool);
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
