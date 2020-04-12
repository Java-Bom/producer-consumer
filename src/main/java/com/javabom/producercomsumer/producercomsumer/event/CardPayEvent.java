package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CardPayEvent extends BankEvent<CardPayRequestDto> {

    public CardPayEvent(CardPayRequestDto dto) {
        this.requestDto = dto;
    }

    @Override
    public void getExecutionText() {
        log.info(requestDto.toString());
        log.info("카드를 결제 했습니다  ◕ˇεˇ◕✿ ");
    }

    @Override
    public String getEventContents() {
        return requestDto.toString();
    }

}
