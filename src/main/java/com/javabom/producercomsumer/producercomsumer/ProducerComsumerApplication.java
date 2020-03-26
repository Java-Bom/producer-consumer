package com.javabom.producercomsumer.producercomsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProducerComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerComsumerApplication.class, args);
    }

}
