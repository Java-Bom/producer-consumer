package com.javabom.producercomsumer.producercomsumer.vendor;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.PayEvent;

import java.util.Arrays;

public enum PayVendorGroup {
    CARD_PAY_VENDOR(CardPayEvent.class, new CardPayVendor()), CASH_PAY_VENDOR(CashPayEvent.class, new CashPayVendor());

    private final Class<?> payEvent;
    private final PayVendor<? extends PayEvent> payVendor;

    PayVendorGroup(Class<? extends PayEvent> payEvent, PayVendor<? extends PayEvent> payVendor) {
        this.payEvent = payEvent;
        this.payVendor = payVendor;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PayEvent> PayVendor<T> findByPayEvent(Class<T> payEvent) {
        return (PayVendor<T>) Arrays.stream(values())
                .filter(payVendorGroup -> payVendorGroup.getPayEvent().equals(payEvent))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .getPayVendor();
    }

    public Class<?> getPayEvent() {
        return payEvent;
    }

    public PayVendor<? extends PayEvent> getPayVendor() {
        return payVendor;
    }
}
