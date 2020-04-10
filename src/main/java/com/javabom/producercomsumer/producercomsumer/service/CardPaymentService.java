package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.service.exception.PayFailException;
import com.javabom.producercomsumer.producercomsumer.vendor.PayVendor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardPaymentService {

    private final AccountRepository accountRepository;
    private final PayVendor cardPayVendor;

    public void requestPay(final CardPaymentRequestDto cardPaymentRequestDto) {
        EventBrokerGroup.findByPayEvent(CardPayEvent.class).offer(new CardPayEvent(cardPaymentRequestDto));
    }

    @Transactional
    public void pay(final CardPayEvent paymentEvent) {
        try {
            cardPayVendor.requestPayToVendor(paymentEvent);
        } catch (PayFailException payFailException) {
            log.info("카드결제 처리 시 익셉션 발생: {} ", paymentEvent.toString());
            requestPay(paymentEvent);
            return;
        }

        Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);

        account.cardPay(paymentEvent.getCardPaymentRequestDto(), true);
    }

    private void requestPay(CardPayEvent paymentEvent) {
        if (paymentEvent.isMaximumTry()) {
            log.info("카드결제시도 횟수를 초과하여 실패내역 기록합니다: {}", paymentEvent.toString());
            recordFailToCardPayment(paymentEvent);
            return;
        }
        log.info("카드결제 이벤트큐에 다시 삽입: {}", paymentEvent.toString());
        EventBrokerGroup.findByPayEvent(CardPayEvent.class).offer(paymentEvent);
    }

    private void recordFailToCardPayment(CardPayEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cardPay(paymentEvent.getCardPaymentRequestDto(), false);
    }

}
