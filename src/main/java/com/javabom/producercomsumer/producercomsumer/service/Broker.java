package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.JavaBomBank;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/03/21
 */
// 큐의 역할을 한다. 이벤트 생성하는 역할을 한다. // extends event
// pull 할 때 .comma를 해야한다.
@Slf4j
public class Broker<E> {

    private Queue<E> bankEventQueue;

    public Broker() {
        this.bankEventQueue = new LinkedList<>();
    }

    public void  registerEvent(E javaBomBank){
        bankEventQueue.add(javaBomBank);
    }

    public E consumeEvent(){
        return bankEventQueue.poll();
    }

    public String getRetainEvents(){
        String queueContents = bankEventQueue.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        return String.format("retain events : %s", queueContents);
    }
}
