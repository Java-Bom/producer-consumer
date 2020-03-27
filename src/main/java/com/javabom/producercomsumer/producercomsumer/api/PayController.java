package com.javabom.producercomsumer.producercomsumer.api;

import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.service.CardPaymentService;
import com.javabom.producercomsumer.producercomsumer.service.CashPaymentService;
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

    private final CashPaymentService cashPaymentService;
    private final CardPaymentService cardPaymentService;

    @PostMapping("/cash")
    public ResponseEntity<String> pay(@RequestBody CashPaymentRequestDto cashPaymentRequestDto) {
        cashPaymentService.requestPay(cashPaymentRequestDto);
        return ResponseEntity.ok("정상적으로 지불되었습니다");
    }

    @PostMapping(value = "/card")
    public ResponseEntity<String> charge(@RequestBody CardPaymentRequestDto cardPaymentRequestDto) {
        cardPaymentService.requestPay(cardPaymentRequestDto);
        return ResponseEntity.ok("정상적으로 지불되었습니다");
    }

}
