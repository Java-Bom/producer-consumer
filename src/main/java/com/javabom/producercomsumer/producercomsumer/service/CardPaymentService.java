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
            Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                    .orElseThrow(IllegalArgumentException::new);
            account.cardPay(paymentEvent.getCardPaymentRequestDto(), true);
            throw new PayFailException("abdaas");
        } catch (PayFailException payFailException) {
            log.info("카드결제 처리 시 익셉션 발생: {} ", paymentEvent.toString());
            requestPay(paymentEvent);
        }
        // 롤백이 안된다. @Transcational 에서 Try- catch 문을 돌리면 롤백안됨.
    }

    private void requestPay(CardPayEvent paymentEvent) {
        if (paymentEvent.isMaximumTry()) {
            log.info("카드결제시도 횟수를 초과하여 실패내역 기록합니다: {}", paymentEvent.toString());
            recordFailToCardPayment(paymentEvent);
            return;
        }

        // consume 하는 스레드가 이벤트큐에 다시 삽입하는 것 까지 하게 되면, 이벤트 큐는 동기화처리 되어야한다.
        // 그럼 이 스레드는 자신이 큐에 넣을 때까지 반환되지 못하기 때문에 놀고있는 스레드가 생기게되는 문제.
        // 그렇다면? consume 하는 스레드는 consume 까지만 해야함. 에러가 생기면 에러만 던지고 바로 스레드풀로 돌아가야함.
        // 그리고 그 에러를 처리하는 것은 메인스레드에서 해야겠지요?
        log.info("카드결제 이벤트큐에 다시 삽입: {}", paymentEvent.toString());
        EventBrokerGroup.findByPayEvent(CardPayEvent.class).offer(paymentEvent);
    }

    private void recordFailToCardPayment(CardPayEvent paymentEvent) {
        Account account = accountRepository.findAccountByUserId(paymentEvent.getCardPaymentRequestDto().getUserId())
                .orElseThrow(IllegalArgumentException::new);
        account.cardPay(paymentEvent.getCardPaymentRequestDto(), false);
    }

}
