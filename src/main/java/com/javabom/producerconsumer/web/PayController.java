package com.javabom.producerconsumer.web;

import com.javabom.producerconsumer.event.CardPayEvent;
import com.javabom.producerconsumer.event.PayRequestBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pay")
@RequiredArgsConstructor
public class PayController {

    private final PayRequestBroker<CardPayEvent> payBroker;

    @PostMapping
    public ResponseEntity<Void> requestPay(@RequestBody CardPayEvent event) {
        payBroker.push(event);
        return ResponseEntity.ok().build();
    }
}
