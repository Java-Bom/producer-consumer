package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;


/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CashPayEvent extends BankEvent<CashPayRequestDto> {

    public CashPayEvent(CashPayRequestDto dto, Consumer<CashPayRequestDto> payRecord) {
        this.requestDto = dto;
        this.payRecord = payRecord;
    }

    @Override
    public void consumeEvent() {
        log.info("현금을 결제 했습니다  ◕ˇεˇ◕✿ :" + requestDto.toString());
        payRecord.accept(requestDto);
    }

    @Override
    public String failEventMessage() {
        return "현금결제요청을 실패했어요  ( Ĭ ^ Ĭ ) :" + requestDto.toString();
    }

    @Override
    public String getEventContents() {
        return requestDto.toString();
    }


}
