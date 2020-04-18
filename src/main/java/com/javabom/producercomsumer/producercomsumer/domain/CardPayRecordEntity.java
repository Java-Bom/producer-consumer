package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CardPayRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CardPayRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime cardPaymentDateTime;

    private String cardName;

    private int price;

    private boolean complete;

    public CardPayRecordEntity(CardPayRequestDto cardPayRequestDto, boolean complete) {
        this.cardName = cardPayRequestDto.getCardCompany();
        this.price = cardPayRequestDto.getPrice();
        this.complete = complete;
    }
}
