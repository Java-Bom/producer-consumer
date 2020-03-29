package com.javabom.producercomsumer.producercomsumer.pay.service;

import com.javabom.producercomsumer.producercomsumer.pay.broker.EventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.broker.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {

    public void addEvent(Event event) {
        log.info("addEvent : " + event);
        EventBroker<Event> eventBroker = EventBrokerGroup.findByEvent(event);
        eventBroker.add(event);
    }

    public Long saveEvent(Event event) {
        log.info("saveEvent : " + event);
        return 1L;
    }
}
