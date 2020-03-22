package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class MoneyPay<E> extends JavaBomBank<E>{

    public MoneyPay(E dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("결제 했습니다 ๑◕‿◕๑ ");
    }
}
