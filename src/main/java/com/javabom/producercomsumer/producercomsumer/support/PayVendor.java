package com.javabom.producercomsumer.producercomsumer.support;


import com.javabom.producercomsumer.producercomsumer.event.PaymentEvent;

public interface PayVendor {
    boolean requestPayToVendor(PaymentEvent paymentEvent);
}
