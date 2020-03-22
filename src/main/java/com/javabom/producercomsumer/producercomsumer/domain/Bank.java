package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class Bank {

    public Consumer<PayRequestDto> pay() {
        return (payRequestDto) -> log.info("Bank //  payType - {}, price - {}", payRequestDto.getPayType(), payRequestDto.getPrice());
    }

    public Consumer<ChargeRequestDto> charge() {
        return (chargeRequestDto) -> log.info("Bank // description - {}, price - {} ", chargeRequestDto.getDescription(), chargeRequestDto.getPrice());
    }

}
