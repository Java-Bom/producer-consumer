package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CardPayEvent<E> extends BankEvnet<E> {

    public CardPayEvent(E dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("카드를 결제 했습니다 ๑◕‿◕๑ ");
    }

}
