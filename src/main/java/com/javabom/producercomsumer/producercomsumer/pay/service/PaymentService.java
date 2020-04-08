package com.javabom.producercomsumer.producercomsumer.pay.service;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.event.PaymentEvent;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.repository.PaymentRepository;
import com.javabom.producercomsumer.producercomsumer.pay.service.dto.PaymentEventRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void addEvent(PaymentEventRequestDto paymentEventRequestDto) {
        log.info("addEvent : " + paymentEventRequestDto);
        PaymentEvent paymentEvent = paymentEventRequestDto.toEvent(this::saveEvent);

        PaymentEventBroker<PaymentEvent> paymentEventBroker = PaymentEventBrokerGroup.findByEvent(paymentEvent);
        paymentEventBroker.add(paymentEvent);
    }

    @Transactional
    public void saveEvent(PaymentEvent paymentEvent) {
        Payment payment = paymentEvent.toEntity();
        Payment save = paymentRepository.save(payment);
        log.info("saveEvent({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }
}
