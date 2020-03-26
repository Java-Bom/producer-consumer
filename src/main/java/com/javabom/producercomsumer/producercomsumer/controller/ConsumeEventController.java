package com.javabom.producercomsumer.producercomsumer.controller;

import com.javabom.producercomsumer.producercomsumer.event.EventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/03/27
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/event")
public class ConsumeEventController {

    private final EventConsumer eventConsumer;

    @GetMapping("/runPay")
    public ResponseEntity<String> runPay(){
        eventConsumer.run();
        log.info("결제 이벤트 수행중");
        return ResponseEntity.ok().body("결제 이벤트 수행중");
    }

    @GetMapping("/stopPay")
    public ResponseEntity<String> stopPayEvent(){
        eventConsumer.shutDownRun();
        log.info("결제 이벤트 수행 중지");
        return ResponseEntity.ok().body("결제 이벤트 수행 중지");
    }

}
