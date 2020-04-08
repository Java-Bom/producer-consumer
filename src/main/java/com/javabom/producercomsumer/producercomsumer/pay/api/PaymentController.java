package com.javabom.producercomsumer.producercomsumer.pay.api;

import com.javabom.producercomsumer.producercomsumer.pay.service.PaymentService;
import com.javabom.producercomsumer.producercomsumer.pay.service.dto.CardEventRequestDto;
import com.javabom.producercomsumer.producercomsumer.pay.service.dto.CashEventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("cash")
    public void addPayEvent(@RequestBody CashEventRequestDto cashEventRequestDto) {
        paymentService.addEvent(cashEventRequestDto);
    }

    @PostMapping("card")
    public void addCardEvent(@RequestBody CardEventRequestDto cardEventRequestDto) {
        paymentService.addEvent(cardEventRequestDto);
    }

}
