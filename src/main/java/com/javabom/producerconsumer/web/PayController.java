package com.javabom.producerconsumer.web;

import com.javabom.producerconsumer.event.message.CardPayEvent;
import com.javabom.producerconsumer.event.message.CashPayEvent;
import com.javabom.producerconsumer.event.process.PayBrokerGroup;
import com.javabom.producerconsumer.service.FailRequestService;
import com.javabom.producerconsumer.service.PayService;
import com.javabom.producerconsumer.web.dto.CardPayRequest;
import com.javabom.producerconsumer.web.dto.CashPayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayBrokerGroup payBrokerGroup;
    private final PayService payService;
    private final FailRequestService failRequestService;

    @PostMapping("/card")
    public ResponseEntity<Void> requestPay(@RequestBody CardPayRequest request) {
        payBrokerGroup.put(CardPayEvent.class, request.toEvent(payService::pay, failRequestService::recordFailRequest));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cash")
    public ResponseEntity<Void> requestPay(@RequestBody CashPayRequest request) {
        payBrokerGroup.put(CashPayEvent.class, request.toEvent(payService::pay, failRequestService::recordFailRequest));
        return ResponseEntity.ok().build();
    }

}
