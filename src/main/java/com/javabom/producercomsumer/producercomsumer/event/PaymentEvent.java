package com.javabom.producercomsumer.producercomsumer.event;

public interface PaymentEvent {
    void run();

    boolean isMaximumTry();
}
