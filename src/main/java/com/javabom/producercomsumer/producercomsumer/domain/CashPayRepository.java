package com.javabom.producercomsumer.producercomsumer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashPayRepository extends JpaRepository<CashPay,Long> {
}
