package com.javabom.producercomsumer.producercomsumer.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jyami on 2020/04/08
 */

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
public class CashPayHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer money;

}
