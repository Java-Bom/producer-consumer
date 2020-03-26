package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CashPayEvent<E> extends BankEvnet<E> {

    public CashPayEvent(E dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("현금을 결제 했습니다 ๑◕‿◕๑ ");
    }
}
