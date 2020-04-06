package com.javabom.producercomsumer.producercomsumer.domain;

import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class CashPaymentRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime cashPaymentLocalDateTime;

    private String productName;

    private boolean complete;

    public CashPaymentRecordEntity(CashPaymentRequestDto cashPaymentRequestDto, boolean complete) {
        this.productName = cashPaymentRequestDto.getProductName();
        this.complete = complete;
    }
}
