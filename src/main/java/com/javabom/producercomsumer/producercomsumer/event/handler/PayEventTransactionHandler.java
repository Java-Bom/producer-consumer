package com.javabom.producercomsumer.producercomsumer.event.handler;

import com.javabom.producercomsumer.producercomsumer.event.CardPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.CashPayEvent;
import com.javabom.producercomsumer.producercomsumer.event.EventBrokerGroup;
import com.javabom.producercomsumer.producercomsumer.service.CardPayService;
import com.javabom.producercomsumer.producercomsumer.service.CashPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayEventTransactionHandler {

    private final CardPayService cardPayService;
    private final CashPayService cashPayService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK, classes = CardPayEvent.class)
    public void afterCardPayServiceRollBack(CardPayEvent payEvent) {
        payEvent.count();
        if (!payEvent.isMaximumTry()) {
            log.info("이벤트큐에 재삽입합니다: {}", Thread.currentThread().getName());
            EventBrokerGroup.findPayEventBroker(CardPayEvent.class).offer(payEvent);
            return;
        }
        log.info("최대 시도횟수를 초과하여 실패내역을 기록합니다: {}", Thread.currentThread().getName());
        new Thread(() -> cardPayService.recordOfFailure(payEvent)).start();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK, classes = CashPayEvent.class)
    public void afterCashPayServiceRollBack(CashPayEvent payEvent) {
        payEvent.count();
        if (!payEvent.isMaximumTry()) {
            log.info("이벤트큐에 재삽입: {}", payEvent);
            EventBrokerGroup.findPayEventBroker(CashPayEvent.class).offer(payEvent);
            return;
        }
        log.info("최대 시도횟수를 초과하여 실패내역을 기록합니다: {}", Thread.currentThread().getName());
        new Thread((() -> cashPayService.recordOfFailure(payEvent))).start();
    }
}
