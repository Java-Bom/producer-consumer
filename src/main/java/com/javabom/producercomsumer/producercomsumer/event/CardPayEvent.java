package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CardPayEvent implements PayEvent {

    private static final String EVENT_NAME = "카드결제이벤트";
    public static final int MAXIMUM_TRY_COUNT = 3;

    private final CardPayRequestDto cardPayRequestDto;

    private int tryCount;

    public CardPayEvent(CardPayRequestDto cardPayRequestDto) {
        this.cardPayRequestDto = cardPayRequestDto;
    }

    public static CardPayEvent of(CardPayRequestDto paymentRequestDto) {
        return new CardPayEvent(paymentRequestDto);
    }

    @Override
    public void run() {
        log.info("CARD PAY 요청: {}, {}", cardPayRequestDto.getCardCompany(), cardPayRequestDto.getPrice());
    }

    @Override
    public boolean isMaximumTry() {
        return tryCount >= MAXIMUM_TRY_COUNT;
    }

    @Override
    public String toString() {
        return "CardPaymentEvent{" +
                "cardPaymentRequestDto=" + cardPayRequestDto +
                '}';
    }

    public void count() {
        tryCount++;
    }
}

