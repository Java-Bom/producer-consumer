package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class Bank {

    public static Consumer<CashPaymentRequestDto> pay() {
        return (payRequestDto) -> log.info("Bank //  payType - {}, price - {}", payRequestDto.getName(), payRequestDto.getPrice());
    }

    public static Consumer<CardPaymentRequestDto> charge() {
        return (chargeRequestDto) -> log.info("Bank // description - {}, price - {} ", chargeRequestDto.getCardCompany(), chargeRequestDto.getPrice());
    }

}
