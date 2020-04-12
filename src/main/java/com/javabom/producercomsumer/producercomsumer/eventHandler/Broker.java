package com.javabom.producercomsumer.producercomsumer.eventHandler;

import com.javabom.producercomsumer.producercomsumer.event.BankEvent;
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
public class Broker<E extends BankEvent<?>> {

    private Queue<E> bankEventQueue;

    public Broker() {
        this.bankEventQueue = new LinkedList<>();
    }

    public void  registerEvent(E javaBomBank){
        bankEventQueue.add(javaBomBank);
    }

    public E consumeEvent(){
        log.info(getRetainEvents());
        return bankEventQueue.poll();
    }

    public boolean hasConsumeEvent(){
        return !bankEventQueue.isEmpty();
    }

    private String getRetainEvents(){
        String queueContents = bankEventQueue.stream()
                .map(c -> c.getEventContents())
                .collect(Collectors.joining(","));

        return String.format("%s retain events : %s", getClassInfo() , queueContents);
    }

    private String getClassInfo(){
        // TODO : 제네릭으로 적용된 타입을 가져오는 방법 ?
        return this.getClass().getCanonicalName();
    }
}
