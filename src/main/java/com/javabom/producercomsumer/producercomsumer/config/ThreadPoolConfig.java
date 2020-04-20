package com.javabom.producercomsumer.producercomsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

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
