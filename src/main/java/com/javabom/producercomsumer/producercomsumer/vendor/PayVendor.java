package com.javabom.producercomsumer.producercomsumer.vendor;

import com.javabom.producercomsumer.producercomsumer.event.PayEvent;

public interface PayVendor<T extends PayEvent> {
    void requestPayToVendor(T paymentEvent);
}
