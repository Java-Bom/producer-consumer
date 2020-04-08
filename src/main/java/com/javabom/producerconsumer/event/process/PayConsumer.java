package com.javabom.producerconsumer.event.process;

import com.javabom.producerconsumer.domain.FailRequestRepository;
import com.javabom.producerconsumer.event.message.PayEvent;
import com.javabom.producerconsumer.exception.FailRequestException;

import java.util.Optional;


// TODO: 2020. 3. 23. 동기화
public class PayConsumer {
    private final PayBrokerGroup payBrokerGroup;
    private final FailRequestRepository failRequestRepository;

    private boolean running = true;

    public PayConsumer(PayBrokerGroup payBrokerGroup, FailRequestRepository failRequestRepository) {
        this.payBrokerGroup = payBrokerGroup;
        this.failRequestRepository = failRequestRepository;
        for (Class<? extends PayEvent> eventClass : payBrokerGroup.keySet()) {
            Thread thread = new Thread(() -> consume(eventClass));
            thread.setName("thread_" + eventClass.getSimpleName());
            thread.start();
        }
    }

    private void consume(Class<? extends PayEvent> eventClass) {
        while (running) {
            Optional<? extends PayEvent> maybeEvent = payBrokerGroup.pop(eventClass);
            if (!maybeEvent.isPresent()) {
                return;
            }

            pay(eventClass, maybeEvent.get());
        }
    }

    private <E extends PayEvent> void pay(Class<E> eventClass, PayEvent payEvent) {
        try {
            payEvent.tryPay();
            payEvent.consume();
        } catch (FailRequestException e) {
            failRequestRepository.save(payEvent.toFail());
        } catch (Exception e) {
            payBrokerGroup.put(eventClass, eventClass.cast(payEvent));
        }
    }

    public void stop() {
        this.running = false;
    }

}
