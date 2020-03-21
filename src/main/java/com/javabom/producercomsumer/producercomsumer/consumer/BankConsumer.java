package com.javabom.producercomsumer.producercomsumer.consumer;

import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.event.JavabomEvent;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BankConsumer<T extends JavabomEvent> {

    public BankConsumer(EventBroker<T> eventBroker) {
        new Thread(() -> {
            while (true) {
                JavabomEvent javabomEvent = eventBroker.poll();
                if (javabomEvent != null) {
                    javabomEvent.comma().accept("BankConsumerWorking!!! ");
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
