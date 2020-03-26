package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.domain.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/03/21
 */
@Service
@RequiredArgsConstructor
public class BankService {

    private final Broker<CashPayEvent<?>> payBroker;
    private final Broker<CardPayEvent<?>> chargeBroker;

    public void registerPayEvent(CardPayRequestDto payReqDto){
        payBroker.registerEvent(new CashPayEvent<>(payReqDto));
    }

    public void registerChargeEvent(CashPayRequestDto chargeReqDto){
        chargeBroker.registerEvent(new CardPayEvent<>(chargeReqDto));
    }
}
