package com.javabom.producercomsumer.producercomsumer.pay.domain.repository;

import com.javabom.producercomsumer.producercomsumer.pay.domain.model.CashPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
}
