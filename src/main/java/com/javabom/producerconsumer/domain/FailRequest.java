package com.javabom.producerconsumer.domain;

import com.javabom.producerconsumer.event.message.PayType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class FailRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PayType payType;

    public FailRequest(PayType payType) {
        this.payType = payType;
    }
}
