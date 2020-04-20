package com.javabom.producercomsumer.producercomsumer.api;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PayController {

    @PostMapping("/cash")
    public ResponseEntity<String> pay(@RequestBody CashPayRequestDto cashPayRequestDto) {
        EventBrokerGroup.findPayEventBroker(CashPayEvent.class).offer(CashPayEvent.of(cashPayRequestDto));
        return ResponseEntity.ok().body("정상적으로 지불되었습니다");
    }

    @PostMapping(value = "/card")
    public ResponseEntity<String> charge(@RequestBody CardPayRequestDto cardPayRequestDto) {
        EventBrokerGroup.findPayEventBroker(CardPayEvent.class).offer(CardPayEvent.of(cardPayRequestDto));
        return ResponseEntity.ok().body("정상적으로 지불되었습니다");
    }

}
