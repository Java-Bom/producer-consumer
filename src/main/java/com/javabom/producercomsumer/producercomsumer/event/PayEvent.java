package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class PayEvent implements JavabomEvent {

    private final PayRequestDto payRequestDto;

    public PayEvent(PayRequestDto payRequestDto) {
        this.payRequestDto = payRequestDto;
    }

    @Override
    public Consumer<String> comma() {
        return payRequestDto.toEntity().pay(payRequestDto.getPrice());
    }

}
