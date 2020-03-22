package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class MoneyCharge<E> extends JavaBomBank<E> {

    public MoneyCharge(E dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("충전 했습니다 ๑◕‿◕๑ ");
    }

}
