package com.javabom.producercomsumer.producercomsumer.config;


import com.javabom.producercomsumer.producercomsumer.domain.BankEvnet;
import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import com.javabom.producercomsumer.producercomsumer.service.Broker;
import com.javabom.producercomsumer.producercomsumer.service.BrokerGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jyami on 2020/03/21
 */
@Configuration
public class EventQueueConfiguration {

    @Bean
    BrokerGroup makeBrokerGroup(){

        Broker<BankEvnet<PayRequestDto>> bankEvnetBroker = new Broker<>();

        return new BrokerGroup(, new Broker<>());
    }
}
