package com.javabom.producercomsumer.producercomsumer.pay.api;

import com.javabom.producercomsumer.producercomsumer.pay.domain.ChargeEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.PayEvent;
import com.javabom.producercomsumer.producercomsumer.pay.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class EventController {

    private final EventService eventService;

    @PostMapping("pay")
    public void addPayEvent(@RequestBody PayEvent payEvent) {
        eventService.addEvent(payEvent);
    }

    @PostMapping("charge")
    public void addChargeEvent(@RequestBody ChargeEvent chargeEvent) {
        eventService.addEvent(chargeEvent);
    }

}
