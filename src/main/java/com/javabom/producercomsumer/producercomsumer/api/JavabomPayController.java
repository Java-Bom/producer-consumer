package com.javabom.producercomsumer.producercomsumer.api;

import com.javabom.producercomsumer.producercomsumer.dto.ChargeRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;
import com.javabom.producercomsumer.producercomsumer.service.ChargeService;
import com.javabom.producercomsumer.producercomsumer.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JavabomPayController {

    private final PayService payService;
    private final ChargeService chargeService;

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PayRequestDto payRequestDto) {
        payService.pay(payRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/charge")
    public ResponseEntity<String> charge(@RequestBody ChargeRequestDto chargeRequestDto) {
        log.info("ChargeRequestDto: {}", chargeRequestDto);
        chargeService.charge(chargeRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
