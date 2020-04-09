package com.javabom.producercomsumer.producercomsumer.pay.domain.repository;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CardPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPaymentRepository extends JpaRepository<CardPayment, Long> {
}
