package com.javabom.producercomsumer.producercomsumer.domain;

/**
 * Created by jyami on 2020/03/21
 */
public abstract class BankEvent<E>{
    protected E requestDto;
    public abstract void getExecutionText();
    public abstract String getEventContents();
}
