package com.javabom.producercomsumer.producercomsumer.vendor;

import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;

public interface PayVendor<T extends PaymentEvent> {
    void requestPayToVendor(T paymentEvent);
}
