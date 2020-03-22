package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class PayEvent implements JavabomEvent {

    private final PayRequestDto payRequestDto;

    public PayEvent(PayRequestDto payRequestDto) {
        this.payRequestDto = payRequestDto;
    }

    @Override
    public void comma() {
        log.info("payType: {}, price:{} 의 결제요청", payRequestDto.getPayType().name(), payRequestDto.getPrice());
    }

}
