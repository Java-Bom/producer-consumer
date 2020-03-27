package com.javabom.producercomsumer.producercomsumer.config;

import com.javabom.producercomsumer.producercomsumer.domain.BankEvent;
import com.javabom.producercomsumer.producercomsumer.event.Broker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by jyami on 2020/03/21
 */
@Configuration
public class EventQueueConfiguration {

    @Bean
    <T extends BankEvent<?>> Broker<T> makeBrokerGroup(){
        return new Broker<T>();
    }

}
