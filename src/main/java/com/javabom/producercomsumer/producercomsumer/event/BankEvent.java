package com.javabom.producercomsumer.producercomsumer.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

/**
 * Created by jyami on 2020/03/21
 */
@Service
@Slf4j
public abstract class BankEvent<E>{
    protected E requestDto;
    protected Consumer<E> payRecord;
    public abstract void consumeEvent();
    public abstract String failEventMessage();
    public abstract String getEventContents();
}
