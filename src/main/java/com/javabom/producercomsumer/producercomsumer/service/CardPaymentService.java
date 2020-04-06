package com.javabom.producercomsumer.producercomsumer.service;

import com.javabom.producercomsumer.producercomsumer.domain.Account;
import com.javabom.producercomsumer.producercomsumer.domain.AccountRepository;
import com.javabom.producercomsumer.producercomsumer.dto.CardPaymentRequestDto;
import com.javabom.producercomsumer.producercomsumer.event.CardPaymentEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBroker;
import com.javabom.producercomsumer.producercomsumer.service.exception.PayFailException;
import com.javabom.producercomsumer.producercomsumer.support.PayVendor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardPaymentService {

    private final EventBroker<CardPaymentEvent> eventBroker;
    private final AccountRepository accountRepository;
    private final PayVendor cardPayVendor;

    public void requestPay(final CardPaymentRequestDto cardPaymentRequestDto) {
        eventBroker.offer(new CardPaymentEvent(cardPaymentRequestDto));
    }

    @Transactional
    public void pay(final CardPaymentEvent paymentEvent) {
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

    private void requestPay(CardPaymentEvent paymentEvent) {
        if (isMaximumTry(paymentEvent)) {
            log.info("카드결제시도 횟수를 초과하여 실패내역 기록합니다: {}", paymentEvent.toString());
            recordFailToCardPayment(paymentEvent);
            return;
        }
        log.info("카드결제 이벤트큐에 다시 삽입: {}", paymentEvent.toString());
        eventBroker.offer(paymentEvent);
    }

    private boolean isMaximumTry(CardPaymentEvent paymentEvent) {
        return paymentEvent.isMaximumTry();
    }

    private void recordFailToCardPayment(CardPaymentEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cardPay(paymentEvent.getCardPaymentRequestDto(), false);
    }

}
