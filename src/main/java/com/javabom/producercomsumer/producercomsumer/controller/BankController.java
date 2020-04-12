package com.javabom.producercomsumer.producercomsumer.controller;

import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyami on 2020/03/21
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/pay")
public class BankController {

    private final BankService bankService;

    @PostMapping("/card")
    public ResponseEntity<String> payCard(@RequestBody CardPayRequestDto cardPayRequestDto){
        bankService.registerCardPayEvent(cardPayRequestDto);
        log.info("카드 결제 요청 : {}", cardPayRequestDto.toString());
        return ResponseEntity.ok().body("카드 결제 요청 " + cardPayRequestDto.toString());
    }


    @PostMapping("/cash")
    public ResponseEntity<String> payCash(@RequestBody CashPayRequestDto cashPayRequestDto){
        bankService.registerCashPayEvent(cashPayRequestDto);
        log.info("현금 결제 요청 : {}", cashPayRequestDto.toString());
        return ResponseEntity.ok().body("현금 결제 요청 " + cashPayRequestDto.toString());
    }

}