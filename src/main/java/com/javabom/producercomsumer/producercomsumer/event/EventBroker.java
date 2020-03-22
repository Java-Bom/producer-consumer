package com.javabom.producercomsumer.producercomsumer.event;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class EventBroker<T extends JavabomEvent> {

    private Queue<T> eventQueue = new LinkedList<>();

    public void offer(T javabomEvent) {
        log.info("offer {} Event in EventBroker", javabomEvent.getClass().getName());
        eventQueue.offer(javabomEvent);
    }

    public T poll() throws InterruptedException {
        while (eventQueue.size() == 0) {
            Thread.sleep(3000);
        }
        return eventQueue.poll();
    }


    /*
     *                                             이벤트 .comma()chldbtjd,2000,cndwjs
     * 1. 이벤트를 결제,충전 api, 결제랑 충전  컨트롤러 결제,충전 -> 서비스 -> 결제이벤트 생성,충전이벤트생성 / 결제이벤트 소비, 충전이벤트 소비
     * 2. 결제는 Enum 타입, 충전은 사유(string)
     * 2. 스레드는 1개로 컴포너트 하나의 큐(브로커)를 공유한다
     * 3. 결제스레드,충전스레드가 존재해서 큐에 들어온 이벤트를 소비한다.
     * */
}
