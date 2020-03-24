package com.javabom.producerconsumer.web;

import com.javabom.producerconsumer.event.CardPayEvent;
import com.javabom.producerconsumer.event.CashPayEvent;
import com.javabom.producerconsumer.event.PayBrokerGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pay")
@RequiredArgsConstructor
public class PayController {


    @PostMapping("/card")
    public ResponseEntity<Void> requestPay(@RequestBody CardPayEvent event) {
        PayBrokerGroup.put(CardPayEvent.class, event);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cash")
    public ResponseEntity<Void> requestPay(@RequestBody CashPayEvent event) {
        PayBrokerGroup.put(CashPayEvent.class, event);
        return ResponseEntity.ok().build();
    }
}
