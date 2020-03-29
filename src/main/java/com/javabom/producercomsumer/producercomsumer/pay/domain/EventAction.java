package com.javabom.producercomsumer.producercomsumer.pay.domain;

public class EventAction {

    public static void pay(Event Event) {
        System.out.println("pay");
    }

    public static void charge(Event event) {
        System.out.println("charge");
    }

}
