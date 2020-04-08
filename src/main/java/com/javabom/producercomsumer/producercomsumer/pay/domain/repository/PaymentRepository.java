package com.javabom.producercomsumer.producercomsumer.pay.domain.repository;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
