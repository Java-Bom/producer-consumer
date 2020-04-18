package com.javabom.producercomsumer.producercomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProducerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerApplication.class, args);
    }

}
