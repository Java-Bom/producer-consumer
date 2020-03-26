package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.BankEvnet;
import com.javabom.producercomsumer.producercomsumer.dto.PayRequestDto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/03/26
 */
public class BrokerGroup {

    private List<Broker<BankEvnet<PayRequestDto>>> brokers;

    @SafeVarargs
    public BrokerGroup(Broker<BankEvnet<PayRequestDto>> ... bomBankBroker) {
        brokers = Collections.unmodifiableList(Arrays.asList(bomBankBroker));
    }

    public List<Broker<BankEvnet<PayRequestDto>>> getBrokers() {
        return brokers;
    }
}
