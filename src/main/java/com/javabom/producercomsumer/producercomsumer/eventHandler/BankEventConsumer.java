package com.javabom.producercomsumer.producercomsumer.eventHandler;

import com.javabom.producercomsumer.producercomsumer.event.BankEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@RequiredArgsConstructor
public class BankEventConsumer<T extends BankEvent<?>> implements Runnable {

    private final Broker<T> broker;

    @Override
    public void run() {
        while(true){
            if(broker.hasConsumeEvent()){
                broker.pollEvent().consumeEvent();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
