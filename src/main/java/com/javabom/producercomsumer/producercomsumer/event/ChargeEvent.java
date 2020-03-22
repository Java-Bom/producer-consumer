package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ChargeEvent implements JavabomEvent {

    private final ChargeRequestDto chargeRequestDto;

    public ChargeEvent(ChargeRequestDto chargeRequestDto) {
        this.chargeRequestDto = chargeRequestDto;
    }

    @Override
    public void comma() {
        log.info("description: {}, price:{} 의 충전요청", chargeRequestDto.getDescription(), chargeRequestDto.getPrice());
    }



}
