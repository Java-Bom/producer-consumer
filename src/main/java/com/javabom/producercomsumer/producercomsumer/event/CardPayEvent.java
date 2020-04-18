package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.domain.CardPayHistory;
import com.javabom.producercomsumer.producercomsumer.domain.CardPayHistoryRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@ToString
public class CardPayEvent extends BankEvent<CardPayRequestDto> {

    public CardPayEvent(CardPayRequestDto dto, Consumer<CardPayRequestDto> payRecord) {
        this.requestDto = dto;
        this.payRecord = payRecord;
    }

    @Override
    public void consumeEvent() {
        log.info(requestDto.toString());
        log.info("카드를 결제 했습니다  ◕ˇεˇ◕✿ ");
        payRecord.accept(requestDto);
    }

    @Override
    public String getEventContents() {
        return requestDto.toString();
    }

}
