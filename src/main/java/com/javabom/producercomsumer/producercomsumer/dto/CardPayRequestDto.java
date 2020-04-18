package com.javabom.producercomsumer.producercomsumer.dto;

import com.javabom.producercomsumer.producercomsumer.domain.CardPayHistory;
import com.javabom.producercomsumer.producercomsumer.domain.CashPayHistory;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/03/21
 */
@Getter
public class CardPayRequestDto extends PayRequestDto {
    private String cardCompanyName;

    @Override
    public String toString() {
        return "CardPayRequestDto{" +
                "cardCompanyName='" + cardCompanyName + '\'' +
                ", money=" + money +
                '}';
    }

    public CardPayHistory of(){
        return CardPayHistory.builder()
                .money(money)
                .company(cardCompanyName)
                .build();
    }
}
