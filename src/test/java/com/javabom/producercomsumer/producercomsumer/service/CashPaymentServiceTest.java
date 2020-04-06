package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CashPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CashPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.support.CashPayVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CashPaymentService.class)
@RunWith(SpringRunner.class)
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass = true)
class CashPaymentServiceTest {

    private static final String USER_ID = "a1010100z@naver.com";
    @Autowired
    CashPaymentService cashPaymentService;
    @MockBean
    CashPayVendor cashPayVendor;
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    EventBroker<CashPaymentEvent> eventEventBroker;

    @AfterEach
    void clearUp() {
        accountRepository.findAccountByUserId(USER_ID).get().clearCardPayHistories();
        accountRepository.findAccountByUserId(USER_ID).get().clearCashPayHistories();
    }

    @DisplayName("현금요청이 실패하면 실패내역을 기록한다")
    @Test
    void failCashPay() {
        //given
        CashPaymentRequestDto cashPaymentRequestDto = CashPaymentRequestDto.builder()
                .productName("감자")
                .price(10000)
                .userId(USER_ID)
                .build();

        CashPaymentEvent cashPaymentEvent = new CashPaymentEvent(cashPaymentRequestDto);

        when(cashPayVendor.requestPayToVendor(cashPaymentEvent)).thenReturn(false);
        when(accountRepository.findAccountByUserId(USER_ID))
                .thenReturn(Optional.ofNullable(new Account(USER_ID)));

        //when
        cashPaymentService.pay(cashPaymentEvent);

        //then
        assertAll(
                () -> assertThat(accountRepository.findAccountByUserId(USER_ID).get().getCashPaymentHistories().size())
                        .isEqualTo(1),
                () -> assertThat(accountRepository.findAccountByUserId(USER_ID).get().getCashPaymentHistories().get(0).isComplete())
                        .isEqualTo(false)
        );
    }

    @DisplayName("현금요청이 성공하면 성공내역을 기록한다")
    @Test
    void passCashPay() {
        //given
        CashPaymentRequestDto cashPaymentRequestDto = CashPaymentRequestDto.builder()
                .productName("감자")
                .price(10000)
                .userId(USER_ID)
                .build();

        CashPaymentEvent cashPaymentEvent = new CashPaymentEvent(cashPaymentRequestDto);

        when(cashPayVendor.requestPayToVendor(cashPaymentEvent)).thenReturn(true);
        when(accountRepository.findAccountByUserId(USER_ID))
                .thenReturn(Optional.ofNullable(new Account(USER_ID)));

        //when
        cashPaymentService.pay(cashPaymentEvent);

        //then
        assertAll(
                () -> assertThat(accountRepository.findAccountByUserId(USER_ID).get().getCashPaymentHistories().size())
                        .isEqualTo(1),
                () -> assertThat(accountRepository.findAccountByUserId(USER_ID).get().getCashPaymentHistories().get(0).isComplete())
                        .isEqualTo(true)
        );
    }

}
