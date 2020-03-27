package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CashPaymentEvent implements PaymentEvent {

    private final CashPaymentRequestDto cashPaymentRequestDto;

    public CashPaymentEvent(CashPaymentRequestDto cashPaymentRequestDto) {
        this.cashPaymentRequestDto = cashPaymentRequestDto;
    }

    @Override
    public void run() {
        log.info("CASHPAY 요쳥: {}, {}", cashPaymentRequestDto.getProductName(), cashPaymentRequestDto.getPrice());
    }

}
