package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.service.Broker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jyami on 2020/03/21
 */
@Configuration
public class EventQueueConfiguration {

    @Bean
    public Broker JavaBomQueue(){
        return new Broker();
    }
}
