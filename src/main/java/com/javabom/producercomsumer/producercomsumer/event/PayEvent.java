package com.javabom.producercomsumer.producercomsumer.event;

public interface PayEvent {
    void run();

    boolean isMaximumTry();
}
