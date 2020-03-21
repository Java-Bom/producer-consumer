package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class ChargeEvent implements JavabomEvent {

    private final ChargeRequestDto chargeRequestDto;

    public ChargeEvent(ChargeRequestDto chargeRequestDto) {
        this.chargeRequestDto = chargeRequestDto;
    }

    @Override
    public Consumer<String> comma() {
        log.info("cargeRequestDto: {}", chargeRequestDto.toString());
        return chargeRequestDto.toEntity().charge(chargeRequestDto.getPrice());
    }

}
