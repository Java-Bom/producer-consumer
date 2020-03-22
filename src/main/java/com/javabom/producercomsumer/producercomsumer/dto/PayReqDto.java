package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/03/21
 */
@Getter
@NoArgsConstructor
public class PayReqDto {
    private PayType payType;
    private Integer hello;

    @Override
    public String toString() {
        return "PayReqDto{" +
                "payType=" + payType.text +
                ", hello=" + hello +
                '}';
    }

    @Getter
    public enum  PayType {
        CASH("현금"), ACCOUNT("계좌"), CARD("카드");

        private String text;

        PayType(String text) {
            this.text = text;
        }
    }
}
