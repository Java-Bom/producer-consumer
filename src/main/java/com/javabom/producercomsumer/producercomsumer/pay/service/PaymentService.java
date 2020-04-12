package com.javabom.producercomsumer.producercomsumer.pay.service;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CardEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.CashEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.repository.CardPaymentRepository;
import com.javabom.producercomsumer.producercomsumer.pay.domain.repository.CashPaymentRepository;
import com.javabom.producercomsumer.producercomsumer.pay.service.dto.CardEventRequestDto;
import com.javabom.producercomsumer.producercomsumer.pay.service.dto.CashEventRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final CardPaymentRepository cardPaymentRepository;
    private final CashPaymentRepository cashPaymentRepository;

    public void addCardEnvent(CardEventRequestDto cardEventRequestDto) {
        log.info("addCashEvent : " + cardEventRequestDto);
        PaymentEvent paymentEvent = cardEventRequestDto.toEvent(this::saveCard);
        PaymentEventBroker<PaymentEvent> paymentEventBroker = PaymentEventBrokerGroup.findByEvent(paymentEvent);
        paymentEventBroker.add(paymentEvent);
    }

    public void addCashEnvent(CashEventRequestDto cashEventRequestDto) {
        log.info("addCashEvent : " + cashEventRequestDto);
        PaymentEvent paymentEvent = cashEventRequestDto.toEvent(this::saveCash);
        PaymentEventBroker<PaymentEvent> paymentEventBroker = PaymentEventBrokerGroup.findByEvent(paymentEvent);
        paymentEventBroker.add(paymentEvent);
    }

    @Transactional
    public void saveCash(CashEvent cashEvent) {
        CashPayment cashPayment = cashEvent.toEntity();
        CashPayment save = cashPaymentRepository.save(cashPayment);
        log.info("saveCashEvent({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }

    @Transactional
    public void saveCard(CardEvent cardEvent) {
        CardPayment cardPayment = cardEvent.toEntity();
        CardPayment save = cardPaymentRepository.save(cardPayment);
        log.info("saveCashEvent({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }
}
