package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CardPayEvent implements PaymentEvent {

    private static final String EVENT_NAME = "카드결제이벤트";
    public static final int MAXIMUM_TRY_COUNT = 3;

    private final CardPaymentRequestDto cardPaymentRequestDto;
    private int tryCount;

    public CardPayEvent(CardPaymentRequestDto cardPaymentRequestDto) {
        this.cardPaymentRequestDto = cardPaymentRequestDto;
    }

    @Override
    public void run() {
        log.info("CARD PAY 요청: {}, {}", cardPaymentRequestDto.getCardCompany(), cardPaymentRequestDto.getPrice());
        tryCount++;
    }

    @Override
    public boolean isMaximumTry() {
        return tryCount >= MAXIMUM_TRY_COUNT;
    }

    @Override
    public String toString() {
        return "CardPaymentEvent{" +
                "cardPaymentRequestDto=" + cardPaymentRequestDto +
                '}';
    }
}

