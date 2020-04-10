package com.javabom.producercomsumer.producercomsumer.web;

import com.javabom.producercomsumer.producercomsumer.event.PayEventBroker;
import com.javabom.producercomsumer.producercomsumer.service.PayService;
import com.javabom.producercomsumer.producercomsumer.web.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.web.dto.CashPayRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PayController {
    private final PayEventBroker payEventBroker;
    private final PayService payService;

    @PostMapping("/card")
    public ResponseEntity<Void> requestPay(@RequestBody CardPayRequestDto cardPayRequestDto) {
        payEventBroker.push(cardPayRequestDto.toEvent());
        payService.save(cardPayRequestDto.toEvent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cash")
    public ResponseEntity<Void> requestPay(@RequestBody CashPayRequestDto cashPayRequestDto) {
        payEventBroker.push(cashPayRequestDto.toEvent());
        payService.save(cashPayRequestDto.toEvent());
        return ResponseEntity.ok().build();
    }
}
