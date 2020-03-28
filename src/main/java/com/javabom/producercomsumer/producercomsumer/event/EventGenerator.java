package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;

public class EventGenerator {

    public static PaymentEvent generate(Object payRequestDto) {
        if (payRequestDto == CardPaymentRequestDto.class) {
            return new CardPaymentEvent((CardPaymentRequestDto) payRequestDto);
        }
        if (payRequestDto == CashPaymentRequestDto.class) {
            return new CardPaymentEvent((CardPaymentRequestDto) payRequestDto);
        }
        throw new IllegalArgumentException();
    }
}
