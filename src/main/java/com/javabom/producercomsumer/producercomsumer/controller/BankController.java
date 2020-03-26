package com.javabom.producercomsumer.producercomsumer.controller;

import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/03/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class BankController {

    private final BankService bankService;

    @PostMapping("/pay")
    public ResponseEntity pay(@RequestBody CardPayRequestDto payReqDto){
        bankService.registerPayEvent(payReqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/charge")
    public ResponseEntity charge(@RequestBody CashPayRequestDto chargeReqDto){
        bankService.registerChargeEvent(chargeReqDto);
        return ResponseEntity.ok().build();
    }

}