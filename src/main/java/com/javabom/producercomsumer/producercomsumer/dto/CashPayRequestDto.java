package com.javabom.producercomsumer.producercomsumer.dto;

import com.javabom.producercomsumer.producercomsumer.domain.CashPayHistory;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/03/21
 */
@Getter
public class CashPayRequestDto extends PayRequestDto {
    private String name;

    @Override
    public String toString() {
        return "CashPayRequestDto{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public CashPayHistory of(){
        return CashPayHistory.builder()
                .money(money)
                .name(name)
                .build();
    }
}
