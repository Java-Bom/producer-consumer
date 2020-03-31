package com.javabom.producercomsumer.producercomsumer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPayRepository extends JpaRepository<CardPay,Long> {
}
