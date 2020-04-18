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

    public void addCardEvent(CardEventRequestDto cardEventRequestDto) {
        log.info("addCashEvent : " + cardEventRequestDto);
        PaymentEvent paymentEvent = cardEventRequestDto.toEvent(this::saveCard);
        supplyToBroker(paymentEvent);
    }

    public void addCashEvent(CashEventRequestDto cashEventRequestDto) {
        log.info("addCashEvent : " + cashEventRequestDto);
        PaymentEvent paymentEvent = cashEventRequestDto.toEvent(this::saveCash);
        supplyToBroker(paymentEvent);
    }

    private void supplyToBroker(final PaymentEvent paymentEvent) {
        try {
            PaymentEventBroker<PaymentEvent> paymentEventBroker = PaymentEventBrokerGroup.findByEvent(paymentEvent);
            paymentEventBroker.add(paymentEvent);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            paymentEvent.failProcess(this::supplyToBroker);
        }
    }

    @Transactional
    public void saveCard(CardEvent cardEvent) {
        CardPayment cardPayment = cardEvent.toEntity();
        CardPayment save = cardPaymentRepository.save(cardPayment);
        log.info("카드 이벤트 저장({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }

    @Transactional
    public void saveCash(CashEvent cashEvent) {
        CashPayment cashPayment = cashEvent.toEntity();
        CashPayment save = cashPaymentRepository.save(cashPayment);
        log.info("현금 이벤트 저장({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }
}
