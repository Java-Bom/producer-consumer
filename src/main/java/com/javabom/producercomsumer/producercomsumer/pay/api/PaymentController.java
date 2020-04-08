package com.javabom.producercomsumer.producercomsumer.pay.api;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import com.javabom.producercomsumer.producercomsumer.pay.service.PaymentService;
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
    public void addPayEvent(@RequestBody CashPayment cashPay) {
        paymentService.addEvent(cashPay);
    }

    @PostMapping("card")
    public void addChargeEvent(@RequestBody CardPayment cardPay) {
        paymentService.addEvent(cardPay);
    }

}
