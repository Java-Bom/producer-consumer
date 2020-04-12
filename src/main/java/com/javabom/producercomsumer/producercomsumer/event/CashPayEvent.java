package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CashPayEvent extends BankEvent<CashPayRequestDto> {

    public CashPayEvent(CashPayRequestDto dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("현금을 결제 했습니다 ๑◕‿◕๑ ");
    }

    @Override
    public String getEventContents() {
        return requestDto.toString();
    }


}
