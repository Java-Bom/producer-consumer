package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.MoneyCharge;
import com.javabom.producercomsumer.producercomsumer.domain.MoneyPay;
import com.javabom.producercomsumer.producercomsumer.dto.ChargeReqDto;
import com.javabom.producercomsumer.producercomsumer.dto.PayReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/03/21
 */
@Service
@RequiredArgsConstructor
public class BankService {

    private final Broker<MoneyPay<PayReqDto>> payBroker;
    private final Broker<MoneyCharge<ChargeReqDto>> chargeBroker;

    public void registerPayEvent(PayReqDto payReqDto){

        payBroker.registerEvent(new MoneyPay<>(payReqDto));
    }

    public void registerChargeEvent(ChargeReqDto chargeReqDto){
        chargeBroker.registerEvent(new MoneyCharge<>(chargeReqDto));
    }
}
