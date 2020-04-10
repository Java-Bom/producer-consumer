package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CashPayEvent implements PaymentEvent {

    private static final String EVENT_NAME = "현금결제이벤트";
    public static final int MAXIMUM_TRYCOUNT = 2;

    private final CashPaymentRequestDto cashPaymentRequestDto;
    private int tryCount;

    public CashPayEvent(CashPaymentRequestDto cashPaymentRequestDto) {
        this.cashPaymentRequestDto = cashPaymentRequestDto;
    }

    @Override
    public void run() {
        log.info("CASHPAY 요쳥: {}, {}", cashPaymentRequestDto.getProductName(), cashPaymentRequestDto.getPrice());
        tryCount++;
    }

    @Override
    public boolean isMaximumTry() {
        return tryCount >= MAXIMUM_TRYCOUNT;
    }

    @Override
    public String toString() {
        return "CashPaymentEvent{" +
                "cashPaymentRequestDto=" + cashPaymentRequestDto +
                '}';
    }
}
