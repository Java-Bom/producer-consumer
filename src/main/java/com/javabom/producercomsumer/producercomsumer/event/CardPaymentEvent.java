package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CardPaymentEvent implements PaymentEvent {

    private static final String EVENT_NAME = "카드결제이벤트";
    private final CardPaymentRequestDto cardPaymentRequestDto;

    public CardPaymentEvent(CardPaymentRequestDto cardPaymentRequestDto) {
        this.cardPaymentRequestDto = cardPaymentRequestDto;
    }

    @Override
    public void run() {
        log.info("CARD PAY 요청: {}, {}", cardPaymentRequestDto.getCardCompany(), cardPaymentRequestDto.getPrice());
    }

    @Override
    public String toString() {
        return "CardPaymentEvent{" +
                "cardPaymentRequestDto=" + cardPaymentRequestDto +
                '}';
    }
}

