package com.javabom.producercomsumer.producercomsumer.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/03/21
 */
@Getter
@ToString
public class CardPayRequestDto extends PayRequestDto {
    private String cardCompanyName;
}
