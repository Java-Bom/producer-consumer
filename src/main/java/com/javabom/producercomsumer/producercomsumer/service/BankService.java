package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.CardPayHistoryRepository;
import com.javabom.producercomsumer.producercomsumer.domain.CashPayHistoryRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import com.javabom.producercomsumer.producercomsumer.dto.CashPayRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * Created by jyami on 2020/03/21
 */
@Service
@RequiredArgsConstructor
public class BankService {

    private final CardPayHistoryRepository cardPayHistoryRepository;
    private final CashPayHistoryRepository cashPayHistoryRepository;

    public Consumer<CardPayRequestDto> saveCardPayHistory(){
        return s -> cardPayHistoryRepository.save(s.of());
    }

    public Consumer<CashPayRequestDto> saveCashPayHistory(){
        return s -> cashPayHistoryRepository.save(s.of());
    }
}
