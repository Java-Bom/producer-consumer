package com.javabom.producercomsumer.producercomsumer.pay.service;

import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBroker;
import com.javabom.producercomsumer.producercomsumer.pay.broker.PaymentEventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import com.javabom.producercomsumer.producercomsumer.pay.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void addEvent(Payment payment) {
        log.info("addEvent : " + payment);
        PaymentEventBroker<Payment> paymentEventBroker = PaymentEventBrokerGroup.findByEvent(payment);
        paymentEventBroker.add(payment);
    }

    @Transactional
    public void saveEvent(Payment payment) {
        Payment save = paymentRepository.save(payment);
        log.info("saveEvent({}) id: {}", save.getClass().getSimpleName(), save.getId());
    }
}
