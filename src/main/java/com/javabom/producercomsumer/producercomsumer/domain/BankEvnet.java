package com.javabom.producercomsumer.producercomsumer.domain;

/**
 * Created by jyami on 2020/03/21
 */
public abstract class BankEvnet<E>{
    protected E requestDto;
    public abstract void getExecutionText();
}
