package com.javabom.producercomsumer.producercomsumer.api;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PayController {

    private final ApplicationEventPublisher publisher;

    @PostMapping("/cash")
    public ResponseEntity<String> pay(@RequestBody CashPayRequestDto cashPayRequestDto) {
        publisher.publishEvent(cashPayRequestDto);
        return ResponseEntity.ok().body("정상적으로 지불되었습니다");
    }

    @PostMapping(value = "/card")
    public ResponseEntity<String> charge(@RequestBody CardPayRequestDto cardPayRequestDto) {
        publisher.publishEvent(cardPayRequestDto);
        return ResponseEntity.ok().body("정상적으로 지불되었습니다");
    }

}
