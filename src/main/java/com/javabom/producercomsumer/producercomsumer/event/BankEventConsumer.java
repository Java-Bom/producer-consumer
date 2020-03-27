package com.javabom.producercomsumer.producercomsumer.event;

import com.javabom.producercomsumer.producercomsumer.domain.BankEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/03/21
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BankEventConsumer<T extends BankEvent<?>> implements Runnable {

    private boolean nowRun = false;
    private final Broker<T> broker;

    @SneakyThrows
    @Override
    public void run() {
        nowRun = true;
        while(nowRun){
            if(broker.hasConsumeEvent()){
                broker.consumeEvent().getExecutionText();
            }
            Thread.sleep(3000);
        }
    }

    public void shutDownRun(){
        nowRun = false;
    }

}
